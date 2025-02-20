# AutoConfiguration and Actuator

## Creating a Custom Spring Boot Failure Analyzer -- checking if service is up
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
    
## custom health check
- see DogsHealthIndicator

# Management endpoint details
- see application.yml

# Custom Spring Boot Actuator
- call http://localhost:8081/sbip/metrics/api.courses.created.count  (CourseTrackerMetricsConfiguration)
- Counter Metric: a single numeric value that is incremented but lost on restart as its not persisted
- Gauge Metric: a db query can be exposed to gauge metric http://localhost:8081/sbip/metrics/api.courses.created.gauge
- Timer: measure time taken for an event: http://localhost:8081/sbip/metrics/api.courses.creation.time
- Distribution Summary: measure the distribution of events