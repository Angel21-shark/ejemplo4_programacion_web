SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname='agenda-web';

DROP DATABASE IF EXISTS "agenda-web";

CREATE DATABASE "agenda-web";

\c agenda-web
CREATE EXTENSION unaccent;

BEGIN;
\i create.sql
COMMIT;
