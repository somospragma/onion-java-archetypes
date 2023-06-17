package com.architecture.archetype.application.factory;

import com.architecture.archetype.application.command.SaveUserCommand;
import com.architecture.archetype.domain.model.UserModel;

/**
 * Clase para mapear comandos(DTO) de entrada a modelos de dominio
 */
//TODO: se puede agregar mapstruct y lombok
public class FactoryUser {

    public UserModel userCommandToUser(SaveUserCommand saveUserCommand) {
        return new UserModel(saveUserCommand.getName(), saveUserCommand.getCode(), saveUserCommand.getPassword());
    }
}
