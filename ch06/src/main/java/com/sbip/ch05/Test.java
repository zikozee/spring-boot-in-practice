package com.sbip.ch05;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Feb, 2024
 */

public class Test {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));;
    }
}
