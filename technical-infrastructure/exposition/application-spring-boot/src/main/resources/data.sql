-- DO NOT USE FOR PRODUCTION
-- automatically executed at spring-boot start
-- but must be in src/main/resources and spring.jpa.hibernate.ddl-auto=none

insert into Books (identifier, author, title,  type)  values ('9408133f-ba8b-4c3f-bd5a-29394110e2c1','27632e2b-f430-4a7e-ab1a-b003b9b5a0a0', 'Foundation', 'FICTION');
insert into Books (identifier, author, title,  type)  values ('1d07e8c0-95be-4775-af95-04e845366028','27632e2b-f430-4a7e-ab1a-b003b9b5a0a0', 'Foundation and Empire', 'FICTION');
--insert into Books (identifier, author, title,  type)  values ('e035ccd3-b4e4-4ed2-88fe-52081a558a17','Robert C. Martin', 'Clean Code', 'TECHNOLOGY');
--insert into Books (identifier, author, title,  type)  values ('385c6f2f-c233-455a-9f27-7534e12e29e2','Fake test', 'JPA mapping', 'SOMETHING');

insert into Authors (identifier, first_name, last_name)  values ('27632e2b-f430-4a7e-ab1a-b003b9b5a0a0','Isaac', 'Asimov');

