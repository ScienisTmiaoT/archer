package org.houstondragonacademy.archer.services;

import org.houstondragonacademy.archer.dao.AccountRepository;
import org.houstondragonacademy.archer.dao.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    // notice that we need to mock AccountRepository which is a field of AccountServiceImpl,
    // that's why we need to use @InjectMocks
    @InjectMocks
    private AccountServiceImpl accountService;

    // mock AccountRepository of AccountServiceImpl
    @Mock
    private AccountRepository accountRepository;

    private static Account account1;
    private static Account account2;
    private static String usernameForAccount1 = "account1";
    private static String usernameForAccount2 = "account2";
    private static String idForAccount1 = "111111";
    private static String emailForAccount1 = "test1@test.com";

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        account1 = Account.builder()
                .emailAddress(emailForAccount1)
                .phoneNumber("111-111-1111")
                .userName(usernameForAccount1)
                .build();
        account1.setDelete(true);
        account1.setId(idForAccount1);
        account2 = Account.builder()
                .emailAddress("test2@test.com")
                .phoneNumber("222-222-2222")
                .userName(usernameForAccount2)
                .build();
        account1.setDelete(false);
        Mono<Account> accountMono = Mono.just(account1);
        Flux<Account> accountFlux1 = Flux.just(account1);
        Flux<Account> accountFlux2 = Flux.just(account1, account2);
        Mockito.when(accountRepository.insert(Mockito.any(Account.class))).thenReturn(accountMono);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(accountMono);
        Mockito.when(accountRepository.findAll()).thenReturn(accountFlux2);
        Mockito.when(accountRepository.findById(Mockito.anyString())).thenReturn(accountMono);
        Mockito.when(accountRepository.findByEmailAddress(Mockito.anyString())).thenReturn(accountFlux1);
    }

    @Test
    void createAccount() {
        Mono<Account> accountMono = accountService.createAccount(account1);
        Account account = accountMono.block();
        assertTrue(account != null);
        assertTrue(usernameForAccount1.equals(account.getUserName()));
    }

    @Test
    void updateAccount() {
        Mono<Account> accountMono = accountService.updateAccount(account1, idForAccount1);
        Account account = accountMono.block();
        assertTrue(account != null);
        assertTrue(usernameForAccount1.equals(account.getUserName()));
    }

    @Test
    void findAll() {
        Flux<Account> accountFlux = accountService.findAll();
        List<Account> accounts = new ArrayList<>();
        accountFlux.subscribe(accounts::add);
        assertTrue(accounts.size() == 2);
    }

    @Test
    void findOne() {
        Mono<Account> accountMono = accountService.findOne(idForAccount1);
        Account account = accountMono.block();
        assertTrue(account != null);
        assertTrue(usernameForAccount1.equals(account.getUserName()));
    }

    @Test
    void findByEmailAddress() {
        Flux<Account> accountFlux = accountService.findByEmailAddress(emailForAccount1);
        List<Account> accounts = new ArrayList<>();
        accountFlux.subscribe(accounts::add);
        assertTrue(accounts.size() == 1);
        assertTrue(usernameForAccount1.equals(accounts.get(0).getUserName()));
    }

    @Test
    void delete() {
        Mono<Boolean> accountMono = accountService.delete(idForAccount1);
        Boolean isDelete = accountMono.block();
        assertTrue(isDelete.booleanValue());
    }
}