package com.architecture.archetype.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.architecture.archetype.api.util.TestUtil;
import com.architecture.archetype.application.command.SaveUserCommand;
import com.architecture.archetype.domain.error.RequiredFieldException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc restMock;

    @Test
    void saveUserTestSaveUserSuccess() throws Exception {
        SaveUserCommand userCommand = TestUtil.createSaveUserCommand();

        this.restMock
                .perform(post(URI.create("/archetype/user")).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonMapper.writeValueAsBytes(userCommand)))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.data.name", CoreMatchers.is("test")));
    }

    @Test
    void saveUserTestInvalidPassword() throws Exception {
        SaveUserCommand userCommand = TestUtil.createSaveUserCommandWithInvalidPassword();

        this.restMock
                .perform(post(URI.create("/archetype/user")).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonMapper.writeValueAsBytes(userCommand)))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(HttpStatus.FORBIDDEN.value()))
                .andExpect(jsonPath("$.data", CoreMatchers.nullValue()));
    }

    @Test
    void saveUserTestWithoutName() throws Exception {
        SaveUserCommand userCommand = TestUtil.createSaveUserCommandWithoutName();

        this.restMock
                .perform(post(URI.create("/archetype/user")).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonMapper.writeValueAsBytes(userCommand)))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.data", CoreMatchers.nullValue()));
    }

    @Test
    void validateFieldUserCommand() throws Exception {
        SaveUserCommand userCommand = TestUtil.createSaveUserCommandWithoutName();
        try {
            userCommand.validateFieldUserCommand(userCommand);
        } catch (RequiredFieldException e) {
            // TODO: handle exception
        }

        userCommand = TestUtil.createSaveUserCommandWithoutCode();
        try {
            userCommand.validateFieldUserCommand(userCommand);
        } catch (RequiredFieldException e) {
            // TODO: handle exception
        }

        Assertions.assertThrows(RequiredFieldException.class, () -> {
            SaveUserCommand userCommandWithoutPassword = TestUtil.createSaveUserCommandWithoutPassword();
            userCommandWithoutPassword.validateFieldUserCommand(userCommandWithoutPassword);
        });

    }
}
