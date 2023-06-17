package com.architecture.archetype.infrastructure.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.architecture.archetype.application.response.GenericResponse;
import com.architecture.archetype.domain.error.FoundOnRestrictiveListException;
import com.architecture.archetype.domain.error.InvalidPasswordException;
import com.architecture.archetype.domain.error.RequiredFieldException;
import com.architecture.archetype.domain.error.UnsupportedEncodeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class HandlerErrors extends ResponseEntityExceptionHandler {

    // TODO: constantes de infraestructura ejemplo el formato de fecha tanto para dto de entrada y salida
    // y también hay constantes de negocio(dominio)
    private static final String OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR = "Ocurrió un error favor contactar al administrador.";
    private static final ConcurrentHashMap<String, Integer> CODE_STATUS = new ConcurrentHashMap<>();

    public HandlerErrors() {
        CODE_STATUS.put(FoundOnRestrictiveListException.class.getSimpleName(), HttpStatus.FORBIDDEN.value());
        CODE_STATUS.put(UnsupportedEncodeException.class.getSimpleName(), HttpStatus.SERVICE_UNAVAILABLE.value());
        CODE_STATUS.put(InvalidPasswordException.class.getSimpleName(), HttpStatus.FORBIDDEN.value());
        CODE_STATUS.put(RequiredFieldException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GenericResponse<Object>> handleAllExceptions(Exception excepcion) {

        var nameExcepcion = excepcion.getClass().getSimpleName();
        var message = excepcion.getMessage();
        var code = CODE_STATUS.get(nameExcepcion);

        if (code != null) {
            return ResponseEntity.ok().body(new GenericResponse<>(code, message, null));
        } else {
            log.error("error desconocido handleAllExceptions con nameExcepcion: {} y message: {}", nameExcepcion, message);
            return ResponseEntity.ok().body(new GenericResponse<>(500, OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR, null));
        }
    }

    // @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        var nameExcepcion = ex.getClass().getSimpleName();
        if (null == body) {
            String message;

            if (ex instanceof HttpRequestMethodNotSupportedException || ex instanceof HttpMediaTypeNotSupportedException
                    || ex instanceof HttpMediaTypeNotAcceptableException || ex instanceof MissingPathVariableException
                    || ex instanceof MissingServletRequestParameterException || ex instanceof ServletRequestBindingException
                    || ex instanceof ConversionNotSupportedException || ex instanceof TypeMismatchException
                    || ex instanceof HttpMessageNotReadableException || ex instanceof HttpMessageNotWritableException
                    || ex instanceof MissingServletRequestPartException || ex instanceof NoHandlerFoundException
                    || ex instanceof AsyncRequestTimeoutException) {
                message = ex.getMessage();
            } else if (ex instanceof MethodArgumentNotValidException || ex instanceof BindException) {
                message = this.getBindingMessage((BindException) ex);
            } else {
                message = OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR;
            }
            log.error("error desconocido handleExceptionInternal con nameExcepcion: {} y message: {}", nameExcepcion, message);
            body = new GenericResponse<>(status.value(), message, null);
        } else {
            log.error("error desconocido handleExceptionInternal con nameExcepcion: {} y body: {}", nameExcepcion, body);
            body = new GenericResponse<>(status.value(), body.toString(), null);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private String getBindingMessage(BindException ex) {
        var bindingResult = ex.getBindingResult();
        var errores = bindingResult.getFieldErrors().stream().map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;

        }).toList();

        var mapeador = new ObjectMapper();
        var mensaje = "";
        try {
            // TODO: en lugar de mapear el mensaje a String debería crear un atributo list
            mensaje = mapeador.writeValueAsString(errores);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mensaje;
    }
}