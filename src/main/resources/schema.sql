CREATE TABLE users (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(25),
    password VARCHAR(255),
    email VARCHAR(85),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    enabled BOOLEAN,
    PRIMARY KEY (id)
);
