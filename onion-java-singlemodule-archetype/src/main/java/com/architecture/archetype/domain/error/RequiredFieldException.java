package com.architecture.archetype.domain.error;

import java.io.Serial;

/**
 * Excepcion personalizada para campos requeridos
 *
 */
public class RequiredFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6508567824775716455L;

    public RequiredFieldException(String mensaje) {
        super(mensaje);
    }
}
