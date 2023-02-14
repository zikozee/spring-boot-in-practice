package com.zikozee.booting;

import com.zikozee.booting.configurationpoperties.AppPropertiesRunner;
import com.zikozee.booting.propertysource.DbConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class BootingApplication {

    private static Logger logger = LoggerFactory.getLogger(BootingApplication.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
//        properties.setProperty("server.port", "8085");

        SpringApplication application = new SpringApplication(BootingApplication.class);
        application.setDefaultProperties(properties);
        ConfigurableApplicationContext applicationContext = application.run(args);
        DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
        AppPropertiesRunner appPropertiesRunner = applicationContext.getBean(AppPropertiesRunner.class);
        logger.info(dbConfiguration.toString());
        logger.info(appPropertiesRunner.getAppProperties().toString());
    }

}
