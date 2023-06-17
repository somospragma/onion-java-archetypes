package com.architecture.archetype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Archetype Application API",
        description = "Microservicio que expone las operaciones básicas para un usuario en la aplicación",
        version = "1.0.0",
        contact = @Contact(
                name = "Business", url = "www.business.com"),
        license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")),
        servers = @Server(
                description = "Url Api Gateway. Reemplazar stage por dev, staging o pdn según corresponda.",
                url = "https://host/stage/archetype/user"))
public class ArchetypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchetypeApplication.class, args);
    }

}
