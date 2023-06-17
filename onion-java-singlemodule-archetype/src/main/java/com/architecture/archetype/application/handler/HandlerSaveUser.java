package com.architecture.archetype.application.handler;

import com.architecture.archetype.api.dto.User;
import com.architecture.archetype.application.command.SaveUserCommand;
import com.architecture.archetype.application.factory.FactoryUser;
import com.architecture.archetype.application.response.GenericResponse;
import com.architecture.archetype.application.service.SaveUserUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HandlerSaveUser {

    // TODO: convertir dto a objetos de negocio y se los envia al caso de uso
    // TODO: un solo comando por cada manejador
    private final SaveUserUseCase saveUserUseCase;
    private final FactoryUser factoryUser;

    public GenericResponse<User> execute(SaveUserCommand saveUserCommand) {
        saveUserCommand.validateFieldUserCommand(saveUserCommand);

        User user = this.factoryUser.userCommandToUser(saveUserCommand);
        user = this.saveUserUseCase.saveUser(user);
        return new GenericResponse<>(201, "Operación exitosa.", user);
    }
}
