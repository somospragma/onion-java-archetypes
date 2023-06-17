package com.architecture.archetype.domain.error;

import java.io.Serial;

//TODO: son los errores de negocio

/**
 * Excepcion personalizada para listas restrictivas
 *
 */
public class FoundOnRestrictiveListException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3508567824775716455L;

    public FoundOnRestrictiveListException(String mensaje) {
        super(mensaje);
    }
}
