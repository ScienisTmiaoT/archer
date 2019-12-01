package org.houstondragonacademy.archer.it;

import org.houstondragonacademy.archer.dao.AccountRepository;
import org.houstondragonacademy.archer.dao.entity.Account;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

@Disabled
@DataMongoTest
@ExtendWith(SpringExtension.class)
class MongoDBIntegrationTest {

    @Autowired
    private AccountRepository repository;

    @Test
    void givenValue_whenFindAllByValue_thenFindAccount() {
        repository.save(Account.builder().emailAddress("test@123.com").phoneNumber("181").build()).block();
        Flux<Account> accountFlux = repository.findByEmailAddress("test@123.com");
        System.out.println(accountFlux);
    }

//    @Bean
//    CommandLineRunner accounts(AccountRepository accountRepository){
//        return args -> {
//            accountRepository.deleteAll()
//                    .subscribe(null, null, () -> {
//                        Account admin = Account.builder()
//                                .emailAddress("123@test.com")
//                                .phoneNumber("123")
//                                .build();
//                        accountRepository.save(admin)
//                                .subscribe(System.out::println);
//                    });
//        };
//    }
}
