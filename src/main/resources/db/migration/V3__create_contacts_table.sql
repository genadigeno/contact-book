create table contacts
(
    id      bigserial
        primary key,
    address varchar(255) not null,
    city    varchar(255) not null,
    country varchar(255) not null,
    email   varchar(255) not null,
    mobile  varchar(255) not null,
    user_id integer
        constraint fkna8bddygr3l3kq1imghgcskt8
            references users
            on DELETE cascade
);