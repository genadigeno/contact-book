create table users
(
    id        serial
        primary key,
    enabled   boolean      not null,
    expired   boolean      not null,
    firstname varchar(255) not null,
    lastname  varchar(255) not null,
    locked    boolean      not null,
    password  varchar(255) not null,
    username  varchar(255) not null
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);