USE turnito;

-- Tabla Especialidad
CREATE TABLE Especialidad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL
);

-- Tabla Profesional
CREATE TABLE Profesional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    mail TEXT NOT NULL,
    nroMatricula TEXT NOT NULL
);

-- Tabla intermedia Profesional_Especialidad
CREATE TABLE Profesional_Especialidad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fkProfesional INT NOT NULL,
    fkEspecialidad INT NOT NULL,
    FOREIGN KEY (fkProfesional) REFERENCES Profesional(id),
    FOREIGN KEY (fkEspecialidad) REFERENCES Especialidad(id)
);

-- Tabla Paciente
CREATE TABLE Paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    mail TEXT
,
    contrasena TEXT NOT NULL
);

-- Tabla Estado
CREATE TABLE Estado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    etiqueta ENUM('Cancelado', 'Cumplido', 'Reservado', 'Disponible') NOT NULL
);

-- Tabla Turno
CREATE TABLE Turno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fkPaciente INT NOT NULL,
    fkProfesional INT NOT NULL,
    fkEstado INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    notas TEXT,
    FOREIGN KEY (fkPaciente) REFERENCES Paciente(id),
    FOREIGN KEY (fkProfesional) REFERENCES Profesional(id),
    FOREIGN KEY (fkEstado) REFERENCES Estado(id)
);

-- Tabla Obra Social
CREATE TABLE ObraSocial (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL
);

-- Tabla Plan
CREATE TABLE Plan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    fkObraSocial INT NOT NULL,
    FOREIGN KEY (fkObraSocial) REFERENCES ObraSocial(id)
);

-- Tabla Afiliacion
CREATE TABLE Afiliacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nroAfiliado TEXT NOT NULL,
    fkObraSocial INT NOT NULL,
    fkPaciente INT NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaFin DATE,
    FOREIGN KEY (fkObraSocial) REFERENCES ObraSocial(id),
    FOREIGN KEY (fkPaciente) REFERENCES Paciente(id)
);

-- Inserción de datos
INSERT INTO Estado (etiqueta) VALUES ('Cancelado'), ('Cumplido'), ('Reservado'), ('Disponible');

INSERT INTO Especialidad (nombre) VALUES ('Cardiología'), ('Pediatría'), ('Dermatología');

INSERT INTO Profesional (nombre, apellido, mail, nroMatricula) VALUES
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
