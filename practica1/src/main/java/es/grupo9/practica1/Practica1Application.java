package es.grupo9.practica1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "es.grupo9.practica1")  // Escanea todas las entidades en este paquete y sus subpaquetes
public class Practica1Application {

    public static void main(String[] args) {
        SpringApplication.run(Practica1Application.class, args);
    }
}

