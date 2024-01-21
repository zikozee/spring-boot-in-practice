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