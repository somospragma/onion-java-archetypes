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
        title = "OnBoarding API",
        description = "Microservicio que expone las operaciones básicas para un usuario en la aplicación OnBoarding",
        version = "1.0.0",
        contact = @Contact(
                name = "Pragma S.A", url = "www.pragma.com.co"),
        license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")),
        servers = @Server(
                description = "Url Api Gateway. Reemplazar stage por dev, staging o pdn según corresponda.",
                url = "https://host/stage/onboarding/user"))
public class OnboardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnboardingApplication.class, args);
    }

}
