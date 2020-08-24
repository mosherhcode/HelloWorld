BEGIN TRANSACTION;

DROP TABLE IF EXISTS personnel CASCADE;

CREATE extension IF NOT EXISTS "uuid-ossp";

CREATE TABLE personnel (
        personnel_id UUID DEFAULT uuid_generate_v4 (),
        username varchar NOT NULL,
        first_name varchar NOT NULL,
        last_name varchar NOT NULL,
        email varchar,
        date_of_birth date,
        eye_color varchar, 
        PRIMARY KEY (personnel_id),
        UNIQUE(username)
);


COMMIT TRANSACTION;