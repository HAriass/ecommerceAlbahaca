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


insert into tipo_cuenta (id,nombre,descripcion) values (1,'ADMINISTRADOR','Administrador con permisos para modificar productos de la base de datos');
insert into tipo_cuenta (id,nombre,descripcion) values (2,'CLIENTE','Usuario normal que accedera al catalogo de productos solamente');


-- claramente hay que encriptar las pass
insert into cuenta (id,email,nombre,password,tipo_cuenta_id) values (1,'usuarioadmin@gmail.com','Usuario Administrador','passadmin',1);
insert into cuenta (id,email,nombre,password,tipo_cuenta_id) values (2,'facusanchez105@gmail.com','Usuario Cliente','passcliente',2);


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


