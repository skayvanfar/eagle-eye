/* If there’s a file named schema.sql in the root of the application’s classpath, then
the SQL in that file will be executed against the database when the application starts */
DROP TABLE IF EXISTS licenses;

CREATE TABLE licenses
(
    license_id        VARCHAR(100) PRIMARY KEY NOT NULL,
    organization_id   TEXT                     NOT NULL,
    license_type      TEXT                     NOT NULL,
    product_name      TEXT                     NOT NULL,
    license_max       INT                      NOT NULL,
    license_allocated INT,
    comment           VARCHAR(100),
    createdAt         timestamp                /*NOT NULL*/
);