insert into categoria (id,nombre,descripcion) values (1,'Electrodomesticos','Elementos de cocina y hogar');
insert into categoria (id,nombre,descripcion) values (2,'Tecnologia','Elementos de tecnologia');
insert into categoria (id,nombre,descripcion) values (3,'Ropa','Artículos de moda y vestimenta');
insert into categoria (id,nombre,descripcion) values (4,'Deportes','Artículos deportivos');
insert into categoria (id,nombre,descripcion) values (5,'Juguetería','Juguetes y entretenimiento infantil');
insert into categoria (id,nombre,descripcion) values (6,'Muebles','Muebles para el hogar y oficina');
insert into categoria (id,nombre,descripcion) values (7,'Accesorios','Accesorios de moda y personales');



insert into marca (id,nombre) values (1,'Samsung');
insert into marca (id,nombre) values (2,'Motorola');
insert into marca (id,nombre) values (3,'Banghoo');
insert into marca (id,nombre) values (4,'BGH');
insert into marca (id,nombre) values (5,'Drean');
insert into marca (id,nombre) values (6,'LG');
insert into marca (id,nombre) values (7,'Sony');
insert into marca (id,nombre) values (8,'Apple');
insert into marca (id,nombre) values (9,'Nike');
insert into marca (id,nombre) values (10,'Adidas');
insert into marca (id,nombre) values (11,'Philips');
insert into marca (id,nombre) values (12,'Panasonic');
insert into marca (id,nombre) values (13,'Toshiba');


-- claramente hay que encriptar las pass
insert into cuenta (id,,nombre,password,rol) values (1,'admin','$2b$12$rHZOrB5u1F2DXrH8H0VI/u04nW42/XNaQkMg9a5gqxnRnUd9tjZdm','rol');


insert into estado (id,nombre,descripcion) values (1,'Sin Stock','Producto sin stock');
insert into estado (id,nombre,descripcion) values (2,'En Stock','Producto disponible en stock');
insert into estado (id,nombre,descripcion) values (3,'En Camino','Producto en tránsito para ser reabastecido');
insert into estado (id,nombre,descripcion) values (4,'En Reserva','Producto reservado para un cliente');
insert into estado (id,nombre,descripcion) values (5,'Descontinuado','Producto descontinuado y no disponible para venta');


insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (1,'Drean next eco 6.06','Lavarropas 6kg carga frontal',700000,1,5);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (2,'iPhone 14','Teléfono inteligente Apple con 128GB de almacenamiento',1200000,2,8);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (3,'Nike Air Max','Zapatillas deportivas para correr',150000,4,9);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (4,'Sofá de 3 plazas','Sofá cómodo para sala de estar',500000,6,12);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (5,'TV LED Sony 55 pulgadas','Televisión LED con calidad 4K Ultra HD',800000,1,7);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (6,'Auriculares Sony WH-1000XM4','Auriculares inalámbricos con cancelación de ruido',300000,7,7);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (7,'Lavarropas LG TurboWash 360','Lavarropas de 9kg con funciones avanzadas',850000,1,6);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (8,'Apple Watch Series 8','Reloj inteligente Apple con funciones de salud avanzadas',350000,7,8);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (9,'Camiseta Adidas','Camiseta deportiva de alto rendimiento',100000,4,10);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (10,'Panasonic Lumix GH5','Cámara sin espejo con grabación de video 4K',900000,2,12);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (11,'iPad Pro 11','Tablet Apple con pantalla Liquid Retina de 11 pulgadas',1100000,2,8);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (12,'Samsung Galaxy S21','Teléfono inteligente Samsung con pantalla Dynamic AMOLED',950000,2,1);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (13,'Moto G Power','Teléfono inteligente Motorola con batería de larga duración',500000,2,2);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (14,'Adidas UltraBoost','Zapatillas deportivas con tecnología Boost',180000,4,10);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (15,'Nike Pro Hypercool','Camiseta deportiva con tecnología de ventilación',120000,4,9);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (16,'LG OLED TV 65 pulgadas','Televisor OLED con calidad de imagen Ultra HD',1500000,1,6);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (17,'Banghoo Notebook 14 pulgadas','Laptop de 14 pulgadas con procesador Intel Core i5',600000,2,3);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (18,'BGH Aire Acondicionado Split 3000W','Aire acondicionado split frío/calor',450000,1,4);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (19,'Reloj Samsung Galaxy Watch','Reloj inteligente con seguimiento de actividad física',300000,7,1);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (20,'Philips Hue Luces Inteligentes','Sistema de iluminación inteligente con control por app',250000,2,11);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (21,'Sony PlayStation 5','Consola de videojuegos de nueva generación',1800000,2,7);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (22,'Apple MacBook Air M1','Laptop Apple con procesador M1 y 256GB SSD',1300000,2,8);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (23,'Toshiba Disco Duro Externo 2TB','Disco duro portátil con 2TB de almacenamiento',200000,2,13);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (24,'LG Monitor UltraWide 34 pulgadas','Monitor curvo UltraWide para multitarea',400000,2,6);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (25,'Sony WH-CH710N','Auriculares con cancelación de ruido activa',250000,7,7);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (26,'Apple AirPods Pro','Auriculares inalámbricos con cancelación de ruido',320000,7,8);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (27,'Samsung Tab S7','Tablet con pantalla de 12.4 pulgadas y S-Pen',1050000,2,1);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (28,'Nike ZoomX Vaporfly','Zapatillas de alto rendimiento para maratón',250000,4,9);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (29,'Adidas Predator 20.3','Botines de fútbol con control de balón mejorado',200000,4,10);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (30,'Sony Bravia 75 pulgadas','Televisor LED con calidad de imagen 8K Ultra HD',2300000,1,7);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (31,'Panasonic Microondas Inverter','Microondas con tecnología de cocción uniforme',300000,1,12);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (32,'Toshiba Smart TV 43 pulgadas','Televisión inteligente con aplicaciones integradas',600000,1,13);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (33,'Motorola One Fusion','Teléfono inteligente con 128GB de almacenamiento',450000,2,2);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (34,'Apple iMac 24 pulgadas','Computadora de escritorio todo en uno con procesador M1',1800000,2,8);
insert into producto (id,nombre,descripcion,precio,categoria_id,marca_id) values (35,'BGH Heladera No Frost','Heladera de alta capacidad con tecnología No Frost',850000,1,4);



-- Ese es el formato datetime de la hora
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (1,1,'2024-09-20 13:00:00.590000',1,1,1);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (2,2,'2024-09-20 14:30:00.590000',2,2,2);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (3,1,'2024-09-20 15:45:00.590000',2,3,3);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (4,1,'2024-09-20 16:10:00.590000',1,1,4);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (5,3,'2024-09-20 17:25:00.590000',1,2,5);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (6,1,'2024-09-21 09:10:00.590000',2,4,6);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (7,2,'2024-09-21 10:15:00.590000',2,2,7);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (8,1,'2024-09-21 11:00:00.590000',1,5,8);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (9,1,'2024-09-21 12:05:00.590000',2,2,9);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (10,2,'2024-09-21 12:45:00.590000',1,1,10);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (11,1,'2024-09-21 13:30:00.590000',3,2,11);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (12,3,'2024-09-21 14:15:00.590000',4,3,12);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (13,1,'2024-09-21 15:00:00.590000',3,1,13);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (14,2,'2024-09-21 15:45:00.590000',4,4,14);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (15,1,'2024-09-21 16:30:00.590000',1,5,15);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (16,3,'2024-09-21 17:15:00.590000',3,1,16);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (17,1,'2024-09-22 09:00:00.590000',2,3,17);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (18,2,'2024-09-22 09:45:00.590000',4,4,18);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (19,1,'2024-09-22 10:30:00.590000',3,1,19);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (20,2,'2024-09-22 11:15:00.590000',2,2,20);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (21,1,'2024-09-22 12:00:00.590000',1,4,21);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (22,3,'2024-09-22 12:45:00.590000',2,2,22);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (23,1,'2024-09-22 13:30:00.590000',3,5,23);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (24,2,'2024-09-22 14:15:00.590000',1,1,24);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (25,1,'2024-09-22 15:00:00.590000',2,3,25);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (26,2,'2024-09-22 15:45:00.590000',4,2,26);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (27,3,'2024-09-22 16:30:00.590000',2,4,27);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (28,1,'2024-09-22 17:15:00.590000',3,1,28);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (29,2,'2024-09-22 18:00:00.590000',4,5,29);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (30,1,'2024-09-22 18:45:00.590000',1,1,30);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (31,3,'2024-09-22 19:30:00.590000',2,3,31);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (32,1,'2024-09-23 09:00:00.590000',3,2,32);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (33,2,'2024-09-23 09:45:00.590000',4,4,33);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (34,1,'2024-09-23 10:30:00.590000',3,5,34);
insert into pedido (id,cantidad,fecha_hora,cuenta_id,estado_id,producto_id) values (35,2,'2024-09-23 11:15:00.590000',1,1,35);



