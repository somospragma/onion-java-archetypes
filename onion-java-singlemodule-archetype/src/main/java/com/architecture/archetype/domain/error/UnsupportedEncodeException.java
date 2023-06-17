package com.architecture.archetype.domain.error;

import java.io.Serial;

/**
 * Excepcion personalizada
 *
 */
public class UnsupportedEncodeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5508567824775716455L;

    public UnsupportedEncodeException(String mensaje) {
        super(mensaje);
    }
}
