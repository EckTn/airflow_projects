#/////////////////////////////////////////////////////////////////////
CREATE ROLE ubuntu WITH LOGIN PASSWORD ${POSTGRES_PASSWORD};
CREATE DATABASE airflow;
GRANT ALL PRIVILEGES on database airflow to ubuntu;
ALTER ROLE ubuntu SUPERUSER;
ALTER ROLE ubuntu CREATEDB;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to ubuntu;

\c airflow 

\conninfo
