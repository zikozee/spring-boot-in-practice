## YAML PROPERTY SOURCE
- we need define our own configuration: **YamlPropertySourceFactory.class**
- and then do this **@PropertySource(value = "foo.yml", factory = YamlPropertySourceFactory.class)**

## USING OTHER FILES ASIDE FROM THE DEFAULT application.properties or yaml
- spring.config.name=sbip
- java -jar <application-name.jar> --spring-config.name=sbip
- or in IntelliJ we just set it as program argument
- 
- we can also use java -jar <application-name.jar> --spring.config.location=C:\path-to-file\sbip.yaml
- or in IntelliJ we just set it as program argument

## ORDER READING PROPERTIES
- SpringApplication  **i.e  application.setDefaultProperties(properties)**
- @PropertySource
- Config data file ... default  application.properties (yml), or --spring.config.name=...  or spring.config.location=...
- OS environment variable
- Command line arguments