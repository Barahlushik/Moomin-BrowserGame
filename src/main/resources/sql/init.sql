CREATE DATABASE moomindb;

CREATE SCHEMA public;

create table public.moomins
(
    id integer generated always as identity,
    username varchar(64) not null unique,
    password varchar(64)
);

create table public.sessions
(
    id varchar(64) primary key,
    user_id integer references moomins,
    expires_at timestamp not null
);

create table public.game_sessions
(
    id varchar(64) primary key,
    user_id integer references moomins,
    expires_at timestamp not null
);


create table public.winners
(
    id        integer generated always as identity,
    login     varchar(64) not null,
    count_sec integer     not null
);