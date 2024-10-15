drop table if exists tpersonausuario;
drop sequence if exists tpersonausuario_cpersona_seq;

create sequence tpersonausuario_cpersona_seq;
alter sequence tpersonausuario_cpersona_seq owner to root;

create table tpersonausuario
(
    entity_type    varchar(31) not null,
    cpersona       integer generated by default as identity primary key,
    direccion      varchar(255),
    edad           integer,
    genero         varchar(255),
    identificacion varchar(255),
    nombre         varchar(255),
    telefono       varchar(255),
    ccliente       integer constraint ukdhux3421ibbso3gsm5yi3dnew unique,
    contrasenia    varchar(255),
    estado         boolean
);

alter table tpersonausuario owner to root;

alter sequence tpersonausuario_cpersona_seq owned by tpersonausuario.cpersona;