create table topicos(
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensaje text not null,
    fecha datetime not null,
    primary key(id),
    constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)
);
