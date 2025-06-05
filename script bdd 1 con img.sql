CREATE DATABASE IF NOT EXISTS turnito;

USE turnito;

-- Tabla Especialidad
CREATE TABLE Especialidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL
);

-- Tabla Profesional
CREATE TABLE Profesional (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    mail TEXT NOT NULL,
    matricula TEXT NOT NULL
);

-- Tabla intermedia Profesional_Especialidad
CREATE TABLE Profesional_Especialidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fkProfesional BIGINT NOT NULL,
    fkEspecialidad BIGINT NOT NULL,
    FOREIGN KEY (fkProfesional) REFERENCES Profesional(id) ON DELETE CASCADE,
    FOREIGN KEY (fkEspecialidad) REFERENCES Especialidad(id) ON DELETE CASCADE
);

-- Tabla Paciente
CREATE TABLE Paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    mail TEXT,
    contrasena TEXT NOT NULL
);

-- Tabla Estado
CREATE TABLE Estado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('Cancelado', 'Cumplido', 'Reservado', 'Disponible') NOT NULL
);

-- Tabla Turno
CREATE TABLE Turno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fkPaciente BIGINT NOT NULL,
    fkProfesional BIGINT NOT NULL,
    fkEstado BIGINT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    notas TEXT,
    FOREIGN KEY (fkPaciente) REFERENCES Paciente(id) ON DELETE CASCADE,
    FOREIGN KEY (fkProfesional) REFERENCES Profesional(id) ON DELETE CASCADE,
    FOREIGN KEY (fkEstado) REFERENCES Estado(id)
);

CREATE TABLE Imagen (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fkTurno BIGINT NOT NULL,
    titulo TEXT,
    imagen longblob,
    FOREIGN KEY (fkTurno) REFERENCES Turno(id) ON DELETE CASCADE
);

-- Tabla Obra Social
CREATE TABLE ObraSocial (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL
);

-- Tabla Plan
CREATE TABLE Plan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    fkObraSocial BIGINT NOT NULL,
    FOREIGN KEY (fkObraSocial) REFERENCES ObraSocial(id) ON DELETE CASCADE
);

-- Tabla Afiliacion
CREATE TABLE Afiliacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nroAfiliado TEXT NOT NULL,
    fkObraSocial BIGINT NOT NULL,
    fkPaciente BIGINT NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaFin DATE,
    FOREIGN KEY (fkObraSocial) REFERENCES ObraSocial(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fkPaciente) REFERENCES Paciente(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla Notificacion
CREATE TABLE Notificacion (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    texto TEXT NOT NULL,
    fkTurno BIGINT NOT NULL,
    fkPaciente BIGINT NOT NULL,
    fechaEnvio DATE NOT NULL,
    horaEnvio TIME NOT NULL,
    visible BOOLEAN NOT NULL DEFAULT true,
    FOREIGN KEY (fkTurno) REFERENCES Turno(id) ON DELETE CASCADE,
    FOREIGN KEY (fkPaciente) REFERENCES Paciente(id) ON DELETE CASCADE
);

-- Inserción de datos
INSERT INTO Estado (nombre) VALUES ('Cancelado'), ('Cumplido'), ('Reservado'), ('Disponible');

INSERT INTO Especialidad (nombre) VALUES ('Cardiología'), ('Pediatría'), ('Dermatología');

INSERT INTO Profesional (nombre, apellido, mail, matricula) VALUES
('Joaco', 'Jawer', 'jjawer@gmail.com', 'MAT123'),
('Maru', 'Roitman', 'mroitman@gmail.com', 'MAT456'),
('Caro', 'Guevara', 'cguevara@gmail.com', 'MAT789');

INSERT INTO Profesional_Especialidad (fkProfesional, fkEspecialidad) VALUES
(1, 1), (1, 2), -- Joaco tiene 2 especialidades
(2, 2),          -- Maru solo Pediatría
(3, 3);          -- Caro solo Dermatología

INSERT INTO Paciente (nombre, apellido, mail, contrasena) VALUES
('Martis', 'Fede', 'mfede@gmail.com', '1234'),
('Jorge', 'Lopez', 'maria.lopez@gmail.com', 'abcd'),
('Mert', 'Mamerto', 'memerto@gmail.com', 'pass');

INSERT INTO ObraSocial (nombre) VALUES ('OSDE'), ('Swiss Medical'), ('PAMI');

INSERT INTO Plan (nombre, fkObraSocial) VALUES
('Plan 210', 1),
('Plan 310', 1),
('Plan SMG20', 2),
('Plan Jubilados', 3);

INSERT INTO Afiliacion (nroAfiliado, fkObraSocial, fkPaciente, fechaAlta, fechaFin) VALUES
('AF123456', 1, 1, '2023-01-01', '2024-06-15'),
('AF654321', 2, 2, '2022-06-15', '2025-01-31'),
('AF112233', 3, 3, '2023-11-10', '2024-12-31');

INSERT INTO Turno (fkPaciente, fkProfesional, fkEstado, fecha, hora, notas) VALUES
(1, 1, 3, '2025-04-10', '09:00:00', 'Consulta general'),
(2, 2, 2, '2025-03-25', '11:30:00', 'Control mensual'),
(3, 3, 1, '2025-04-02', '15:00:00', 'Cancelado por el paciente');

-- Inserciones de notificaciones
INSERT INTO Notificacion (texto, fkTurno, fkPaciente, fechaEnvio, horaEnvio, visible) VALUES
('Recordatorio: Tiene un turno reservado para el 10/04/2025 a las 09:00.', 1, 1, '2025-04-09', '10:00:00', true),
('Turno cumplido el 25/03/2025 a las 11:30. Gracias por asistir.', 2, 2, '2025-03-25', '13:00:00', false),
('Su turno del 02/04/2025 fue cancelado.', 3, 3, '2025-04-01', '17:00:00', true),
('Nuevo turno disponible con Dr. Joaco el 15/04/2025.', 1, 1, '2025-04-05', '08:30:00', true),
('Se ha modificado el estado de su turno. Ahora está marcado como cumplido.', 2, 2, '2025-03-26', '09:15:00', false),
('Recordatorio: No olvide traer estudios previos a la consulta.', 1, 1, '2025-04-09', '18:45:00', true);
