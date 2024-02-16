# AutoConfiguration and Actuator

## Creating a Custom Spring Boot Failure Analyzer
- Problem: Check if a service is available at startup, if not don't start and show proper message
- solution
  - create and throw a custom exception through and EventListener or any other application startup runners e.g Commandline runner
    - see UrlAccessibilityHandler
  - create a Customer Failure Analyzer i.e UrlNotAccessibleFailureAnalyzer and extend AbstractFailureAnalyzer<CustomException>
  - register the UrlNotAccessibleFailureAnalyzer in spring.factories under resources/META-INF/spring.factories
- EXAMPLES: 
  - NoSuchBeanDefinitionFailureAnalyzer is invoked when a NoSuchBeanDefinitionException exception occurs.
  - DataSourceBeanCreationFailureAnalyzer, which is invoked whenever a DataSourceBeanCreationException
    - this is where why see **datasource url not defined**

# Management endpoint details
- see application.yml

# Custom Spring Boot Actuator
- call http://localhost:8081/sbip/metrics/api.courses.created.count  (CourseTrackerMetricsConfiguration)
- Counter Metric: a single numeric value that is incremented but lost on restart as its not persisted
- Gauge Metric: a db query can be exposed to gauge metric http://localhost:8081/sbip/metrics/api.courses.created.gauge
- Timer: measure time taken for an event: http://localhost:8081/sbip/metrics/api.courses.creation.time
- Distribution Summary: measure the distribution of events


## Generating Self Signed Certificate
- this is only necessary if we don't have a load balancer to our application
keytool -genkeypair -alias sbip -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore sbip.p12 -validity 3650 -storepass p@ssw0rd
- add to security config
```java
    http requiresChannel(channel -> channel.anyRequest().requiresSecure()) // enforcing ssl
```
- redirecting http://localhost:8080 to https://localhost:8443  through the RedirectHttpToHttpsConfig class
- https://localhost:8443/login


## Installing and Configuring HashiCorp Vault
- vault server -config vault.conf
- vault operator init
- 
```commandline
vault operator unseal qoxaJmOMxSR4koHP0GI4F+NBi3IipXOf57mVJb6k+BYh && \
> vault operator unseal eriDeKRPp0ATMKNp8bNWPqZSZzH1soJCYh2ewIcsXlv+ && \
> vault operator unseal tasPCj0Ygx6eeeH6zM/M5ue6uxDIaoz1w58VPYz0H5uU

```

- export VAULT_TOKEN=<INITIAL ROOT TOKEN>

- vault secrets enable -path=secret kv
- vault write secret/coursetracker keystore=p@ssw0rd

- configure application.yml
```yaml
  spring:  
    cloud:
      vault:
        # place in environment variable VAULT INIT TOKEN, change in EDIT BUILD CONFIGURATION
        token: ${VAULT_TOKEN}
        authentication: token
        host: localhost
        port: 8200
        scheme: http
    config:
      import: vault://secret/coursetracker

    application:
      name: coursetracker
```

- replace passwords or secrets
  - e.g server.ssl.key-store-password=${keystore}