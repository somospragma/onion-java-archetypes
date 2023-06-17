package com.architecture.archetype.domain.error;

import java.io.Serial;

public class InvalidPasswordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4508567824775716455L;

    public InvalidPasswordException(String mensaje) {
        super(mensaje);
    }
}
