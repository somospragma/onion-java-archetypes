package com.architecture.archetype.infrastructure.adapter;

import com.architecture.archetype.domain.driver.gateway.RestrictiveListGateway;

/**
 * Clase para la logica de implementacion de metodos para las listas restrictivas
 */
public class RestrictiveListTransunionAdapter implements RestrictiveListGateway {

    @Override
    public boolean getValidate(String name) {
        return "test".equalsIgnoreCase(name);
    }
}
