# Domain Driven Design sample project

not using lombok because (at least in the domain):
* can be poorly used
* plays poorly with coverage


global test strategy
* junit + spring + mockito for exposition
* junit + spring + mockito for infra
* junit to test non-business cases for equals, hashcode, toString
* cucumber for collaborative business testing from orchestration/presentation to domain/repositories (with in memory implementations) 