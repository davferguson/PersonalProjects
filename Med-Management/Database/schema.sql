BEGIN TRANSACTION;

DROP TABLE IF EXISTS app_user;

CREATE TABLE IF NOT EXISTS app_user
(
    user_id  SERIAL,
    username VARCHAR(50) NOT NULL,
    password_hash VARCHAR(200) NOT NULL,
    role VARCHAR(50),
    CONSTRAINT pk_user_id PRIMARY KEY (user_id),
    CONSTRAINT unique_username UNIQUE (username)
);

COMMIT TRANSACTION;