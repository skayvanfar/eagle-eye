DROP TABLE IF EXISTS organizations;

CREATE TABLE organizations
(
    organization_id VARCHAR(100) PRIMARY KEY NOT NULL,
    name            TEXT                     NOT NULL,
    contact_name    TEXT                     NOT NULL,
    contact_email   TEXT                     NOT NULL,
    contact_phone   TEXT                     NOT NULL
);
