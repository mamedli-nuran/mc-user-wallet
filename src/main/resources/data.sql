-- Добавляем пользователей
INSERT INTO users (id, name, surname, username, email, password, role)
VALUES ('11111111-1111-1111-1111-111111111111', 'Alice', 'Ivanova', 'alice', 'alice@example.com', 'password123',
        'USER'),
       ('22222222-2222-2222-2222-222222222222', 'Bob', 'Petrov', 'bob', 'bob@example.com', 'password123', 'USER');

-- Добавляем кошельки, связывая с пользователями
INSERT INTO wallets (id, user_id, balance, currency, status)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', 100.00, 'AZN', 'ACTIVE'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222', 50.00, 'AZN', 'ACTIVE');