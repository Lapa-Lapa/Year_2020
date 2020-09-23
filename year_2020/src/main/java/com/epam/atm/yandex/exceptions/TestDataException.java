package com.epam.atm.yandex.exceptions;

/**
 * Thrown if an application doesn't succeed to get test data
 * @author Darya_Tarelko
 */
public class TestDataException extends RuntimeException {

    public TestDataException(String message) {
        super(message);
    }
}
