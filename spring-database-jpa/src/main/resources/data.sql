DELETE FROM client;
DELETE FROM tariff;

INSERT INTO tariff (id, name, description, price, is_active) VALUES
                                                                 (1, 'Базовый', 'Базовый пакет услуг', 500.00, true),
                                                                 (2, 'Премиум', 'Расширенный пакет услуг', 1000.00, true);

INSERT INTO client (id, name, email, phone, created_at, tariff_id) VALUES
                                                                       (1, 'Иван Иванов', 'ivan@mail.ru', '+79161234567', NOW(), 1),
                                                                       (2, 'Мария Петрова', 'maria@mail.ru', '+79167654321', NOW(), 2);

ALTER SEQUENCE tariff_id_seq RESTART WITH 3;
ALTER SEQUENCE client_id_seq RESTART WITH 3;