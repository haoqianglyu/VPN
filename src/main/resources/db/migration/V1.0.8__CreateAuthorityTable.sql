CREATE SEQUENCE authority_id_seq;
CREATE TABLE authorities (
    id bigint not null DEFAULT NEXTVAL('authority_id_seq'),
    role varchar(255) not null,

    user_id bigint DEFAULT NULL,
    primary key (id),

    CONSTRAINT fk_server_region
      FOREIGN KEY (user_id)
      REFERENCES users (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);
ALTER SEQUENCE authority_id_seq OWNED BY authorities.id;