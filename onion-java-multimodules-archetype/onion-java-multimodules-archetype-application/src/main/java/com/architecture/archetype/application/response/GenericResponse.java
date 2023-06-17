package com.architecture.archetype.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Clase encargada de definir el objeto de respuesta generico para todas las apis
 * @param <T>
 */
@AllArgsConstructor
@Getter
public class GenericResponse<T> {

    private int status;
    private String responseMessage;
    private T data;

}
