create table respuestas(
    id bigint not null auto_increment,
    mensaje varchar(255) not null,
    topico_id bigint not null,
    fecha_de_creacion datetime not null,
    status varchar(15) not null default 'SOLUCIONADO',
    usuario_id bigint not null,
    primary key(id),
    constraint uk_respuestas_mensaje_topico unique (mensaje,topico_id),
    constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
    constraint fk_respuestas_usuario_id foreign key(usuario_id) references usuarios(id)
);
