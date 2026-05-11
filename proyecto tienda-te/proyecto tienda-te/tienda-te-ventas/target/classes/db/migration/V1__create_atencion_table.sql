CREATE TABLE IF NOT EXISTS atencion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    fecha_atencion DATE NOT NULL,
    hora_atencion TIME NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    id_paciente BIGINT NOT NULL,
    comentario VARCHAR(500),
    PRIMARY KEY (id)
);