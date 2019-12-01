package org.houstondragonacademy.archer.controller;

import lombok.extern.slf4j.Slf4j;
import org.houstondragonacademy.archer.dao.entity.Account;
import org.houstondragonacademy.archer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Flux<Account> findAll() {
        log.debug("findAll all accounts");
        return accountService.findAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Flux<Account> findByEmail(@RequestParam String email) {
        log.debug("findByEmail Account with emailAddress : {}", email);
        return accountService.findByEmailAddress(email);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<Account> findOne(@PathVariable String id) {
        log.debug("findOne Account with uuid : {}", id);
        return accountService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> create(@RequestBody Account account) {
        log.debug("create Account with Account : {}", account);
        return accountService.createAccount(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Mono<Boolean> delete(@PathVariable String id) {
        log.debug("delete Account with id : {}", id);
        return accountService.delete(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Mono<Account> updateAccount(@RequestBody Account account, @PathVariable String id) {
        log.debug("updateAccount Account with id : {} and Account : {}", id, account);
        return accountService.updateAccount(account, id);
    }
}
