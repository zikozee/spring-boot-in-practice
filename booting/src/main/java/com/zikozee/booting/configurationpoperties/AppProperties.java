package com.zikozee.booting.configurationpoperties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author: Ezekiel Eromosei
 * @created: 13 February 2023
 */

@Configuration
@ConfigurationProperties(prefix = "cp.sbip.ct")
@Getter @Setter
@ToString
@NoArgsConstructor
public class AppProperties {
    private String name;
    private String ip;

    private int port;
    private Security security;

    public AppProperties(String name, String ip, @DefaultValue("60000")int port, Security security) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.security = security;
    }

//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter @Setter
//    @ToString
//    public static class Security {
//        private boolean enabled;
//        private String token;
//        private List<String> roles;
//    }

    public record Security(String name, String token, List<String> roles) {}
}
