-- Таблица пользователей
CREATE TABLE users(
    id         UUID PRIMARY KEY,
    name       VARCHAR(255),
    surname    VARCHAR(255),
    username   VARCHAR(255),
    email      VARCHAR(255) UNIQUE,
    password   VARCHAR(255),
    role       VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица кошельков
CREATE TABLE wallets(
    id       UUID PRIMARY KEY,
    user_id  UUID UNIQUE, -- один кошелек на одного пользователя
    balance  DECIMAL(18, 2) DEFAULT 0,
    currency VARCHAR(10)    DEFAULT 'AZN',
    status   VARCHAR(20)    DEFAULT 'ACTIVE',
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);