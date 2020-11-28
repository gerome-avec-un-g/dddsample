-- DO NOT USE FOR PRODUCTION
-- automatically executed at spring-boot start

create table if not exists Books (
    title varchar(50) not null,
    author varchar(50) not null,
    type varchar(50) not null
);