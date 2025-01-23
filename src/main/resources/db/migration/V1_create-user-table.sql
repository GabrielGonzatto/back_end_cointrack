CREATE TABLE usuario (
    id serial not null primary key,
    nome varchar(70) not null,
    apelido varchar(70) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null
);