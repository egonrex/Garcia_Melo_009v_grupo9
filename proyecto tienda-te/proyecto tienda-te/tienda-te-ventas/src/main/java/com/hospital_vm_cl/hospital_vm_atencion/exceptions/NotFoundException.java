package com.hospital_vm_cl.hospital_vm_atencion.exceptions;

// Excepción para recursos que no existen
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}