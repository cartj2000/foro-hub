alter table topicos drop foreign key fk_topicos_usuario_id;
alter table topicos change column usuario_id autor_id bigint not null;
alter table topicos add constraint fk_topicos_autor_id foeign key (autor_id) references usuarios(id);