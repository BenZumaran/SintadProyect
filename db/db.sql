create table clientes
(
id serial primary key,
nombre varchar(50) not null,
apellido varchar(50) not null,
correo varchar(50) not null,
telefono char(9) null,
fecha_registro timestamp,
estado boolean
);

create table users (
    id bigserial primary key,
    username varchar(100) not null unique,
    role varchar(50) not null,
    pepper varchar(255) not null,
    password varchar(255) not null
);


insert into users (username, role, pepper, password) values
('alice', 'user', 'pep123', 'passAlice'),
('bob', 'user', 'pep456', 'passBob'),
('charlie', 'user', 'pep789', 'passCharlie'),
('david', 'admin', 'pep321', 'passDavid'),
('eva', 'admin', 'pep654', 'passEva');



INSERT INTO clientes (nombre, apellido, correo, telefono, fecha_registro, estado) VALUES
('Carlos', 'Ramirez', 'carlos.ramirez1@example.com', '912345678', '2025-09-30 08:15:23.425381', true),
('Maria', 'Fernandez', 'maria.fernandez2@example.com', '923456789', '2025-09-30 09:22:11.123456', true),
('Jose', 'Lopez', 'jose.lopez3@example.com', '934567890', '2025-09-30 10:47:55.654321', true),
('Ana', 'Gonzalez', 'ana.gonzalez4@example.com', '945678901', '2025-09-30 12:33:41.987654', true),
('Luis', 'Martinez', 'luis.martinez5@example.com', '956789012', '2025-09-30 14:58:05.234567', true),
('Carmen', 'Torres', 'carmen.torres6@example.com', '967890123', '2025-09-30 15:42:55.876543', true),
('Jorge', 'Diaz', 'jorge.diaz7@example.com', '978901234', '2025-09-30 18:30:15.112233', true),
('Lucia', 'Castillo', 'lucia.castillo8@example.com', '989012345', '2025-09-30 20:44:55.445566', true),
('Pedro', 'Vargas', 'pedro.vargas9@example.com', '990123456', '2025-09-30 21:11:38.778899', true),
('Elena', 'Mendoza', 'elena.mendoza10@example.com', '901234567', '2025-09-30 22:59:49.332211', true),
('Hugo', 'Sanchez', 'hugo.sanchez11@example.com', '912345679', '2025-10-01 07:24:17.665544', true),
('Sofia', 'Rojas', 'sofia.rojas12@example.com', '923456780', '2025-10-01 08:35:42.998877', true),
('Miguel', 'Paredes', 'miguel.paredes13@example.com', '934567891', '2025-10-01 09:48:56.221100', true),
('Laura', 'Ortega', 'laura.ortega14@example.com', '945678902', '2025-10-01 10:59:11.554433', true),
('Ricardo', 'Morales', 'ricardo.morales15@example.com', '956789013', '2025-10-01 12:12:28.887766', true),
('Gabriela', 'Alvarez', 'gabriela.alvarez16@example.com', '967890124', '2025-10-01 13:44:33.110022', true),
('Fernando', 'Herrera', 'fernando.herrera17@example.com', '978901235', '2025-10-01 14:55:46.443355', true),
('Rosa', 'Chavez', 'rosa.chavez18@example.com', '989012346', '2025-10-01 15:12:57.776688', true),
('Andres', 'Navarro', 'andres.navarro19@example.com', '990123457', '2025-10-01 16:34:21.889900', true),
('Valeria', 'Jimenez', 'valeria.jimenez20@example.com', '901234568', '2025-10-01 17:01:15.223344', true),
('Daniel', 'Silva', 'daniel.silva21@example.com', '912345680', '2025-10-01 18:45:39.556677', true),
('Patricia', 'Cruz', 'patricia.cruz22@example.com', '923456781', '2025-10-01 19:23:55.889922', true),
('Alejandro', 'Reyes', 'alejandro.reyes23@example.com', '934567892', '2025-10-01 20:14:17.112255', true),
('Isabel', 'Guerrero', 'isabel.guerrero24@example.com', '945678903', '2025-10-01 21:42:38.445588', true),
('Manuel', 'Vega', 'manuel.vega25@example.com', '956789014', '2025-10-01 22:52:18.425381', true),
('Paola', 'Campos', 'paola.campos26@example.com', '967890125', '2025-10-02 00:18:27.778811', true),
('Roberto', 'Salazar', 'roberto.salazar27@example.com', '978901236', '2025-10-02 01:59:43.112299', true),
('Natalia', 'Peña', 'natalia.pena28@example.com', '989012347', '2025-10-02 03:22:09.445566', true),
('Diego', 'Cabrera', 'diego.cabrera29@example.com', '990123458', '2025-10-02 04:11:55.778844', true),
('Monica', 'Flores', 'monica.flores30@example.com', '901234569', '2025-10-02 05:42:18.112233', true),
('Oscar', 'Delgado', 'oscar.delgado31@example.com', '912345681', '2025-10-02 06:15:33.445566', true),
('Angela', 'Suarez', 'angela.suarez32@example.com', '923456782', '2025-10-02 07:24:12.778899', true),
('Felipe', 'Cordero', 'felipe.cordero33@example.com', '934567893', '2025-10-02 08:36:55.112233', true),
('Diana', 'Molina', 'diana.molina34@example.com', '945678904', '2025-10-02 09:49:41.445566', true),
('Sebastian', 'Prieto', 'sebastian.prieto35@example.com', '956789015', '2025-10-02 11:12:29.778899', true),
('Veronica', 'Aguilar', 'veronica.aguilar36@example.com', '967890126', '2025-10-02 13:24:55.112233', true),
('Rodrigo', 'Lozano', 'rodrigo.lozano37@example.com', '978901237', '2025-10-02 15:58:41.445566', true);

