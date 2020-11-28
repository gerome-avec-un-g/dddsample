-- DO NOT USE FOR PRODUCTION
-- automatically executed at spring-boot start
-- but must be in src/main/resources and spring.jpa.hibernate.ddl-auto=none

insert into Books (author, title,  type)  values ('Isaac Asimov', 'Foundation', 'FICTION');
insert into Books (author, title,  type)  values ('Isaac Asimov', 'Foundation and Empire', 'FICTION');
insert into Books (author, title,  type)  values ('Robert C. Martin', 'Clean Code', 'TECHNOLOGY');
insert into Books (author, title,  type)  values ('Fake test', 'JPA mapping', 'SOMETHING');

