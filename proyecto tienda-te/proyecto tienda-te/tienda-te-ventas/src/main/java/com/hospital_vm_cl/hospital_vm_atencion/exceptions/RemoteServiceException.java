package com.hospital_vm_cl.hospital_vm_atencion.exceptions;
// Excepción para errores al consumir otro microservicio
public class RemoteServiceException extends RuntimeException {
    public RemoteServiceException(String message) {
        super(message);
    }
}