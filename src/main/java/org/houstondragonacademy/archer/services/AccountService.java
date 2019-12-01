package org.houstondragonacademy.archer.services;

import org.houstondragonacademy.archer.dao.entity.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> createAccount(Account account);

    Mono<Account> updateAccount(Account account, String id);

    Flux<Account> findAll();

    Mono<Account> findOne(String id);

    Flux<Account> findByEmailAddress(String email);

    Mono<Boolean> delete(String id);

}
