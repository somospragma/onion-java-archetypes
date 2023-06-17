package com.architecture.archetype.application.dto;

import java.util.regex.Pattern;

import com.architecture.archetype.domain.error.InvalidPasswordException;
import com.architecture.archetype.domain.utility.EncodeUtility;

//TODO: este no tiene validaciones de negocio, es un modelo anemico
//TODO: los modelos tienen la lógica de negocios, ejemplo si solo se pueden crear usuarios mayores de 18
//TODO: los casos de uso se usan cuando necesita lógica mas complicada
//TODO: no debería usarse aquí, porque se hace lógica de negocio

/**
 * Modelo del usuario
 */
public class User {

    private String name;
    private String code;
    private String password;

    public User(String name, String code, String password) {
        this.name = name;
        this.code = code;
        if (this.validatePassword(password)) {
            this.password = EncodeUtility.encode(password);
        } else {
            throw new InvalidPasswordException("Contraseña no cumple criterios");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getPassword() {
        return EncodeUtility.decode(this.password);
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)([A-Za-z\\d]|[^ ]){8,}$");
        return pattern.matcher(password).matches();
    }
}
