package org.houstondragonacademy.archer.dao;

import org.houstondragonacademy.archer.dao.entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findByEmailAddress(String email);

    Flux<Account> findByEmailAddressAndDeleteIsTrue(String email);

    Mono<Account> findByPhoneNumber(String phoneNumber);

    Mono<Account> findByPhoneNumberAndDeleteIsTrue(String phoneNumber);

    Mono<Account> findByIdAndDeleteIsTrue(String id);
}
