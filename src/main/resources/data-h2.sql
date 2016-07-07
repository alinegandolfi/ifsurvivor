-- USER
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (1, 'cliente@123.123', 'Cliente', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');

-- ROLES
insert into roles(id, role) values (1, 'ROLE_CLIENT');

-- USER_ROLES
insert into users_roles (user_id, role_id) values (1, 1);


-- MEAL
insert into meal(id, description, name, price) values (1, 'Massa com ovos, carne de gado, amendoim torrado e molho satay.', 'Combo 1', 20.5);
insert into meal(id, description, name, price) values (2, 'Arroz jasmin, frango, abacaxi assado e molho bangkok.', 'Combo 2', 21.5);
insert into meal(id, description, name, price) values (3, 'Massa de arroz, carne de porco, gergelim e molho woking.', 'Combo 3', 21.5);
insert into meal(id, description, name, price) values (4, 'Arroz jasmin, carne de gado, e molho bangkok.', 'Combo 4', 22.5);

--ORDERS
insert into orders(id, date, total_price, client_id, meal_id, quantity) values (1, sysdate, '123.0', 1, 1, 6);
insert into orders(id, date, total_price, client_id, meal_id, quantity) values (2, sysdate, '86.0', 1, 2, 4);
insert into orders(id, date, total_price, client_id, meal_id, quantity) values (3, sysdate, '102.5', 1, 1, 5);


--USER_ORDERS
insert into user_orders(user_id, orders_id) values (1, 1);
insert into user_orders(user_id, orders_id) values (1, 2);
insert into user_orders(user_id, orders_id) values (1, 3);