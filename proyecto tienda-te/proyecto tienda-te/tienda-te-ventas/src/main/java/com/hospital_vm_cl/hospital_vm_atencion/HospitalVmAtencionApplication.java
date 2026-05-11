package com.hospital_vm_cl.hospital_vm_atencion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HospitalVmAtencionApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalVmAtencionApplication.class, args);
	}

}
