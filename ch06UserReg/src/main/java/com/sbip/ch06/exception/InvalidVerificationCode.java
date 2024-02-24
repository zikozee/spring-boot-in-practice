package com.sbip.ch06.exception;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

public class InvalidVerificationCode extends RuntimeException{

    public InvalidVerificationCode(String message) {
        super(message);
    }
}
