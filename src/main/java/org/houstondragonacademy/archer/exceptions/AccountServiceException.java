package org.houstondragonacademy.archer.exceptions;

import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class AccountServiceException extends RuntimeException{
    @NotNull String message;
}
