CREATE TABLE IF NOT EXISTS paciente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    rut VARCHAR(13) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    fecha_nacimiento DATE NULL,
    correo VARCHAR(150) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_paciente_rut (rut)
);