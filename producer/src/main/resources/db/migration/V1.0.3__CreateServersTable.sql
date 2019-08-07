CREATE SEQUENCE server_id_seq;
CREATE TABLE servers (
    id bigint not null DEFAULT NEXTVAL('server_id_seq'),

    region_id bigint DEFAULT NULL,
    primary key (id),

    CONSTRAINT fk_server_region
      FOREIGN KEY (region_id)
      REFERENCES regions (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);
ALTER SEQUENCE server_id_seq OWNED BY servers.id;