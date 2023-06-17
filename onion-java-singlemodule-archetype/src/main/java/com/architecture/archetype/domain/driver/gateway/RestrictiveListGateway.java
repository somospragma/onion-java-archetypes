package com.architecture.archetype.domain.driver.gateway;

/**
 * Interfaz que permite administrar las operaciones a realizar sobre la data de listas restrictivas
 *
 * @version 1.0
 */
public interface RestrictiveListGateway {
    boolean getValidate(String name);
}
