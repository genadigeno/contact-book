create table public.roles (
    id int generated always as identity,
    "name" varchar(150),
    primary key (id)
);