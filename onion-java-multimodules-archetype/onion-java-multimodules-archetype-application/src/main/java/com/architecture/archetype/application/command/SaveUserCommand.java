package com.architecture.archetype.application.command;

import com.architecture.archetype.domain.error.RequiredFieldException;

import lombok.Getter;
import lombok.Setter;

/**
 * Comando(DTO) de entrada para guardar un usuario
 * 
 * @version 1.0
 */
@Setter
@Getter
public class SaveUserCommand {

    private String name;
    private String code;
    private String password;

    public void validateFieldUserCommand(SaveUserCommand saveUserCommand) {
        if ((null == saveUserCommand.getName()) || saveUserCommand.getName().isBlank()) {
            throw new RequiredFieldException("Name es obligatorio.");
        }

        if ((null == saveUserCommand.getCode()) || saveUserCommand.getCode().isBlank()) {
            throw new RequiredFieldException("Code es obligatorio.");
        }

        if ((null == saveUserCommand.getPassword()) || saveUserCommand.getPassword().isBlank()) {
            throw new RequiredFieldException("Password es obligatorio.");
        }
    }
}
