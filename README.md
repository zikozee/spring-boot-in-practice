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
- keytool -genkeypair -alias sbip -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore sbip.p12 -validity 3650 -storepass p@ssw0rd
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
      
          OR
- use docker-compose  -->>> too tricky
- create key.pem and certificate.pem
  - openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem
- Review the created certificate
- openssl x509 -text -noout -in certificate.pem
- Combine your key and certificate in a PKCS#12 (P12) bundle [Optional not needed here]
-  openssl pkcs12 -inkey key.pem -in certificate.pem -export -out certificate.p12

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


# max Login Attempts
- we internally use quava for caching
- listening for the AuthenticationFailureBadCredentialsEvent for wrong input details
  - we check the username and cache number of failed attempts, and expire after 24 hrs if and admin does not clear
- and AuthenticationSuccessEvent for successful entry
  -  where we clear the old bad attempts from cache

# remember me
- we implemented the in memory
- we can choose to switch to persistent
- token storage remember me
- 
# recaptcha
- best used when multiple login is detected

# liveness and readiness probe
- management.endpoint.health.probes.enabled=true
- manually delaying readiness and/or liveness probe to do some work see :-> setup/SetupManagingLivenessAndReadiness

# Swagger -Open API specification
- http://localhost:8080/swagger-ui/index.html
- you an export as json: -> http://localhost:8080/v3/api-docs
- which can be viewed or imported in: https://editor.swagger.io/

# Versioning
- URI versioning - uses a version number in the URI
- Request parameter versioning - Uses an HTTP request parameter to identify the version
- Custom HTTP header versioning  - Uses an HTTP request header to distinguish the version
- Media type versioning - Uses the accept request header in the request to identify the version 

# security
- used spring microservice in action auth server