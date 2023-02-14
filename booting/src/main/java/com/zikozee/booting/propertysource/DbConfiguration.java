package com.zikozee.booting.propertysource;

import com.zikozee.booting.propertysource.yaml.YamlPropertySourceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author: Ezekiel Eromosei
 * @created: 13 February 2023
 */

@Configuration
@PropertySource(value = "dbconfig.properties")
@PropertySource(value = "foo.yml", factory = YamlPropertySourceFactory.class)
@RequiredArgsConstructor
public class DbConfiguration {

    private final Environment environment;

    @Override
    public String toString() {
        return "\nUsername: " + environment.getProperty("user")
                + "\nPassword:" + environment.getProperty("password")
                + "\nyaml name: " + environment.getProperty("data.name")
                + "\nyaml aliases: " + environment.getProperty("data.aliases[0]")
                + "\nTIMEOUT FROM OS env: " + environment.getProperty("app.timeout");
    }
}
