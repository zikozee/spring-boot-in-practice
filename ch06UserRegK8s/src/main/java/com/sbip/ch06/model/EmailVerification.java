package com.sbip.ch06.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

@Setter
@Getter
@Entity
@Table(name = "CT_EMAIL_VERIFICATIONS")
@NoArgsConstructor
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)//*generator = "UUID_GENERATOR"*/ )
//    @GenericGenerator(name ="UUID_GENERATOR", type = GenerationType.UUID)
    private String verificationId;
    private String username;

    public EmailVerification(String username) {
        this.username = username;
    }
}
