-- USER
--INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (1, '123@123.123', 'General User', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
--INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (2, 'admin@123.123', 'Administrador', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (1, 'cliente@123.123', 'Cliente', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');

-- ROLES
--insert into roles(id, role) values (1, 'ROLE_USER');
--insert into roles(id, role) values (2, 'ROLE_ADMIN');
insert into roles(id, role) values (1, 'ROLE_CLIENT');

-- USER_ROLES
insert into users_roles (user_id, role_id) values (1, 1);
--insert into users_roles (user_id, role_id) values (2, 2);
--insert into users_roles (user_id, role_id) values (3, 3);

-- MEAL
insert into meal(id, description, name, price) values (1, 'Massa com ovos, carne de gado, amendoim torrado e molho satay.', 'Combo 1', 20.5);
insert into meal(id, description, name, price) values (2, 'Arroz jasmin, frango, abacaxi assado e molho bangkok.', 'Combo 2', 21.5);

--ORDERS
insert into orders(id, date, total_price, client_id) values (1, sysdate, '20.6', 1);
insert into orders(id, date, total_price, client_id) values (2, sysdate, '20.6', 1);

--ORDERS_MEALS 
insert into orders_meals(orders_id, meals_id) values (1, 2);
--insert into orders_meals(orders_id, meals_id) values (2, 1);
insert into orders_meals(orders_id, meals_id) values (2, 2);

--USER_ORDERS
insert into user_orders(user_id, orders_id) values (1, 1);
insert into user_orders(user_id, orders_id) values (1, 2);