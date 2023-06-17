package com.architecture.archetype.domain.utility;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.architecture.archetype.domain.error.UnsupportedEncodeException;

import lombok.experimental.UtilityClass;

/**
 * Clase utilitaria para codificar y decodificar contraseñas
 */
@UtilityClass
public class EncodeUtility {

    public static String decode(String s) {
        try {
            return new String(Base64.getDecoder().decode(s.getBytes("UTF8")),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodeException("No se puede codificar");
        }
    }

    public static String encode(String s) {
        try {
            return new String(Base64.getEncoder().encode(s.getBytes("UTF8")),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodeException("No se puede decodificar");
        }
    }

}
