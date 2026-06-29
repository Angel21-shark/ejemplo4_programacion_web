INSERT INTO cat_tipo_contacto (id_tipo_contacto, tx_nombre, st_activo) VALUES (nextval('cat_tipo_contacto_id_seq'), 'Teléfono Fijo', true) ON CONFLICT DO NOTHING;
INSERT INTO cat_tipo_contacto (id_tipo_contacto, tx_nombre, st_activo) VALUES (nextval('cat_tipo_contacto_id_seq'), 'Teléfono Móvil', true) ON CONFLICT DO NOTHING;
INSERT INTO cat_tipo_contacto (id_tipo_contacto, tx_nombre, st_activo) VALUES (nextval('cat_tipo_contacto_id_seq'), 'Correo Electrónico', true) ON CONFLICT DO NOTHING;
INSERT INTO cat_tipo_contacto (id_tipo_contacto, tx_nombre, st_activo) VALUES (nextval('cat_tipo_contacto_id_seq'), 'Red Social (X)', true) ON CONFLICT DO NOTHING;
INSERT INTO cat_tipo_contacto (id_tipo_contacto, tx_nombre, st_activo) VALUES (nextval('cat_tipo_contacto_id_seq'), 'Red Social (Facebook)', true) ON CONFLICT DO NOTHING;
