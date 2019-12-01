package org.houstondragonacademy.archer.dao.entity;

import lombok.*;
import org.houstondragonacademy.archer.dao.entity.Children;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "accounts")
@EqualsAndHashCode(of = {"emailAddress"}, callSuper = true)
@Builder
public class Account extends BaseEntity {
    enum AccountRoles {
        ADMINISTRATOR,
        STAFF,
        USER,
    }
    enum AccountType {
        PARENT,
        CHILD,
    }

    @NonNull
    private String emailAddress;

    @NonNull
    private String phoneNumber;

    @Indexed
    private String userName;

    private AccountRoles accountRoles;
    private AccountType accountType;
    private String homeAddress;
    private Children[] children;
    private String notes;
}
