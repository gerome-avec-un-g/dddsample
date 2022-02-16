# Domain Driven Design sample project

## Run the application

```
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

## Dependencies

###Lombok
not using lombok because (at least in the domain):
* can be poorly used
* plays poorly with coverage
* Intellij has code generation with Alt + Insert

###Cucumber
see src/test/java/fr/geromeavecung/exposition/presentation/cucumber/README.md

##Global test strategy

* cucumber for collaborative business testing from presentation to domain with in memory
  implementations of repositories
* junit to test remaining "technical" cases (mostly some path for equals, hashcode, toString...)
* junit + spring + mockito for exposition
* junit + spring + mockito for infra

TODO maven mvnw ?  
thymeleaf th:text placeholder good practice ?

spring à revoir  
3.1 jdbc  
4.2 security et datasource  
4.3.4 csrf à faire chaque form ?  
5.1.2 jdbc configuration 5.1.3 server config (specially ssl)