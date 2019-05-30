create sequence regions_id_seq;

create table regions(
    id bigint not null DEFAULT NEXTVAL('regions_id_seq'),
    country_name varchar(255) not null,
    server_name varchar (255) not null,

    primary key (id)
);

ALTER sequence regions_id_seq OWNED BY regions.id;