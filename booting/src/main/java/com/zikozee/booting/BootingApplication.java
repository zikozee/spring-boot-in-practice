package com.zikozee.booting;

import com.zikozee.booting.configurationpoperties.AppPropertiesRunner;
import com.zikozee.booting.constraint.model.Course;
import com.zikozee.booting.constraint.model.User;
import com.zikozee.booting.propertysource.DbConfiguration;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;
import java.util.Set;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class BootingApplication implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        Course course = new Course();
        course.setId(1);
        course.setRating(0);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Course>> violations = validator.validate(course);

        violations.forEach(courseConstraintViolation ->  logger.error("A constraint violation has occurred. Violation details: [{}]", courseConstraintViolation));


        User user1 = new User("sbip01", "sbip");
        Set<ConstraintViolation<User>> violations1 = validator.validate(user1);
        logger.error("Password for user1 do not adhere to the password policy");
        violations1.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user2 = new User("sbip02", "Sbip01$4UDfg");
        violations1 = validator.validate(user2);
        if(violations1.isEmpty()) {
            logger.info("Password for user2 adhere to the password policy");
        }

        User user3 = new User("sbip03", "Sbip01$4UDfgggg");
        violations1 = validator.validate(user3);
        logger.error("Password for user3 violates maximum repetitive rule");
        violations1.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user4 = new User("sbip04", "Sbip014UDfgggg");
        violations1 = validator.validate(user4);
        logger.error("Password for user4 violates special character rule");
        violations1.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));
    }
}
