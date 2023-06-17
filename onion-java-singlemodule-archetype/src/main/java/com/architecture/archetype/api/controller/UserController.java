package com.architecture.archetype.api.controller;

//import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.architecture.archetype.api.dto.User;
import com.architecture.archetype.application.command.SaveUserCommand;
import com.architecture.archetype.application.handler.HandlerSaveUser;
import com.architecture.archetype.application.response.GenericResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/archetype")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
@Tag(name = "UserController", description = "Endpoint de apis REST para guardar de usuarios en el dominio de usuarios.")
public class UserController {

    private final HandlerSaveUser handlerSaveUser;

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Agregar usuario", description = "Permite recibir una petición de agregar un usuario. Este evalua los campos obligatorios, existencia y formatos para antes de crear el elemento en el sistema")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto JSON con la informacion del producto", required = true, content = @Content(schema = @Schema(implementation = SaveUserCommand.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario Creado", content = @Content(schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "400", description = "Los datos recibidos no cumplen con la obligatoriedad o formatos esperados", content = @Content(schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = GenericResponse.class))) })
    public GenericResponse<User> saveUser(@NotNull @RequestBody SaveUserCommand saveUserCommand) {
        return this.handlerSaveUser.execute(saveUserCommand);
    }
}
