create table usuarios (
	id varchar(50) primary key,
	apelido varchar(50) not null unique,
    username varchar(50) not null unique,
    senha varchar(150) not null,
    email varchar(50) not null unique,
    telefone varchar(50) not null unique,
    tipo_de_usuario varchar(50) not null,
    enabled bool default true
);