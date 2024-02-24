package com.sbip.ch06.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Entity
@Table(name = "Ct_TOTP_DETAILS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TotpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String secret;

    public TotpDetails(String username, String secret) {
        this.username = username;
        this.secret = secret;
    }
}
