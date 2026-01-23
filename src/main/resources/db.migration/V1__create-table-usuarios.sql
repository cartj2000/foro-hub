create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    login varchar(100) not null,
    contrasena varchar(255) not null,
    primary key(id)
);
