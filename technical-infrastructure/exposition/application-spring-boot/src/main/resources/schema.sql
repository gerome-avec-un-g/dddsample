-- DO NOT USE FOR PRODUCTION
-- automatically executed at spring-boot start
-- but must be in src/main/resources and spring.jpa.hibernate.ddl-auto=none

create table if not exists Books (
    identifier varchar(36) not null,
    title varchar(50) not null,
    author varchar(50) not null,
    type varchar(50) not null
);