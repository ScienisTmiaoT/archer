package org.houstondragonacademy.archer.services;

import org.houstondragonacademy.archer.dao.AccountRepository;
import org.houstondragonacademy.archer.dao.entity.Account;
import org.houstondragonacademy.archer.exceptions.AccountServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repo;

    @Override
    public Mono<Account> createAccount(Account account) {
        return repo.insert(account);
    }

    @Override
    public Mono<Account> updateAccount(Account account, String id) {
        return findOne(id)
                .doOnSuccess(accountDoc -> {
                    accountDoc.setAccountRoles(account.getAccountRoles());
                    accountDoc.setAccountType(account.getAccountType());
                    accountDoc.setEmailAddress(account.getEmailAddress());
                    accountDoc.setHomeAddress(account.getHomeAddress());
                    accountDoc.setPhoneNumber(account.getPhoneNumber());
                    accountDoc.setUserName(account.getUserName());
                    accountDoc.setChildren(account.getChildren());
                    accountDoc.setNotes(account.getNotes());
                    repo.save(accountDoc).subscribe();
                });
    }

    @Override
    public Flux<Account> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Account> findOne(String id) {
        return repo.findById(id);
    }

    @Override
    public Flux<Account> findByEmailAddress(String email) {
        return repo.findByEmailAddress(email)
                .switchIfEmpty(Mono.error(new AccountServiceException("User not exist with email containing" + email)));
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return findOne(id)
                .doOnSuccess(accountDoc -> {
                    accountDoc.setDelete(true);
                    repo.save(accountDoc).subscribe();
                })
                .flatMap(accountDoc -> Mono.just(accountDoc.getDelete()));
    }
}
