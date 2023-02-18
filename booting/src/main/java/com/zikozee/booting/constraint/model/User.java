package com.zikozee.booting.constraint.model;

import com.zikozee.booting.constraint.custom.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: Ezekiel Eromosei
 * @created: 18 February 2023
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private String username;

    @Password
    private String password;
}
