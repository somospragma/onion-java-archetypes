package com.architecture.archetype.api.util;

import com.architecture.archetype.application.command.SaveUserCommand;

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
    
    public static SaveUserCommand createSaveUserCommandWithoutCode() {
        SaveUserCommand userCommand = new SaveUserCommand();
        userCommand.setName("test");
        userCommand.setPassword("testPassword1");
        return userCommand;
    }
    
    public static SaveUserCommand createSaveUserCommandWithoutPassword() {
        SaveUserCommand userCommand = new SaveUserCommand();
        userCommand.setName("test");
        userCommand.setCode("1");
        return userCommand;
    }
}
