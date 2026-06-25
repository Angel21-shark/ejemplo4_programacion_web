CREATE TABLE cag01_tipo_contacto (id_tipo SERIAL NOT NULL, tx_nombre varchar(100) NOT NULL, tx_descripcion varchar(255) NOT NULL, st_activo bool NOT NULL, PRIMARY KEY (id_tipo));
CREATE TABLE tag01_usuario (id_usuario SERIAL NOT NULL, tx_nombre varchar(100) NOT NULL, tx_primer_apellido varchar(100) NOT NULL, tx_segundo_apellido varchar(100), tx_username varchar(50) NOT NULL UNIQUE, tx_password varchar(256) NOT NULL, PRIMARY KEY (id_usuario));
CREATE TABLE tag02_contacto (id_contacto SERIAL NOT NULL, fk_id_usuario int4 NOT NULL, tx_nombre varchar(100) NOT NULL, tx_primer_apellido varchar(100) NOT NULL, tx_segundo_apellido varchar(100), tx_apodo varchar(20) NOT NULL, PRIMARY KEY (id_contacto));
CREATE TABLE tag03_medio_contacto (id_medio_contacto SERIAL NOT NULL, fk_id_contacto int4 NOT NULL, fk_id_tipo int4 NOT NULL, tx_valor varchar(255) NOT NULL, PRIMARY KEY (id_medio_contacto));
ALTER TABLE tag02_contacto ADD CONSTRAINT FKtag02_cont548851 FOREIGN KEY (fk_id_usuario) REFERENCES tag01_usuario (id_usuario);
ALTER TABLE tag03_medio_contacto ADD CONSTRAINT FKtag03_medi265747 FOREIGN KEY (fk_id_contacto) REFERENCES tag02_contacto (id_contacto);
ALTER TABLE tag03_medio_contacto ADD CONSTRAINT FKtag03_medi581297 FOREIGN KEY (fk_id_tipo) REFERENCES cag01_tipo_contacto (id_tipo);
