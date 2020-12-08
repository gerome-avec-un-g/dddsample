# Domain Driven Design sample project

not using lombok because (at least in the domain):
* can be poorly used
* plays poorly with coverage


global test strategy
* junit + spring + mockito for exposition
* junit + spring + mockito for infra
* junit to test non-business cases for equals, hashcode, toString
* cucumber for collaborative business testing from orchestration/presentation to domain/repositories (with in memory implementations)

mvn spring-boot:run -Dspring-boot.run.profiles=local

TODO  
maven mvnw ?  
thymeleaf th:text placeholder good practice ?


spring à revoir  
3.1 jdbc  
4.2 security et datasource  
4.3.4 csrf à faire chaque form ?  
5.1.2 jdbc configuration
5.1.3 server config (specially ssl)