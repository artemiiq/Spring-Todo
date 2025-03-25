CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

INSERT INTO users (username, password, enabled) VALUES ('user', '{noop}jdbcDefault', true);
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
