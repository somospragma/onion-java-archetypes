package com.architecture.archetype.api.util;

import com.architecture.archetype.application.command.SaveUserCommand;
import com.architecture.archetype.application.dto.User;
import com.architecture.archetype.application.response.GenericResponse;

public class TestUtil {

    private TestUtil() {
    }

    public static SaveUserCommand createSaveUserCommand() {
        SaveUserCommand userCommand = new SaveUserCommand();
        userCommand.setName("test");
        userCommand.setCode("1");
        userCommand.setPassword("testPassword1");
        return userCommand;
    }

    public static GenericResponse<User> responseUser() {
        User userDto = new User("test", "1", "testPassword122222222");
        return new GenericResponse<>(201, "Operación exitosa.", userDto);
    }

    public static SaveUserCommand createSaveUserCommandWithInvalidPassword() {
        SaveUserCommand userCommand = new SaveUserCommand();
        userCommand.setName("test");
        userCommand.setCode("1");
        userCommand.setPassword("test");
        return userCommand;
    }

    public static SaveUserCommand createSaveUserCommandWithoutName() {
        SaveUserCommand userCommand = new SaveUserCommand();
        userCommand.setCode("1");
        userCommand.setPassword("testPassword1");
        return userCommand;
    }
}
