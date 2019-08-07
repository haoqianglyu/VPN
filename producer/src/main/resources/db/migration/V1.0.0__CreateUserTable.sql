create sequence users_id_seq;

create table users(
    id bigint not null DEFAULT NEXTVAL('users_id_seq'),
    email varchar(255) not null,
    first_name varchar (255) not null,
    last_name varchar(255) not null,
    primary key (id)
);

ALTER sequence users_id_seq OWNED BY users.id;