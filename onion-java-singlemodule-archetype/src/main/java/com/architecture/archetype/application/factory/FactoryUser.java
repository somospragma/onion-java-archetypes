package com.architecture.archetype.application.factory;

import com.architecture.archetype.api.dto.User;
import com.architecture.archetype.application.command.SaveUserCommand;

/**
 * Clase para mapear comandos(DTO) de entrada a modelos de dominio
 */
// TODO: se puede agregar mapstruct y lombok
public class FactoryUser {

    public User userCommandToUser(SaveUserCommand saveUserCommand) {
        return new User(saveUserCommand.getName(), saveUserCommand.getCode(), saveUserCommand.getPassword());
    }
}
