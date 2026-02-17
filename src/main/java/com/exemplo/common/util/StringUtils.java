package com.exemplo.common.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Utilitarios para manipulacao de Strings.
 * 
 * ONDE FICA: common/util/
 * 
 * EXEMPLOS DE USO:
 * - StringUtils.removerAcentos("cafe") -> "cafe"
 * - StringUtils.apenasNumeros("123-456") -> "123456"
 */
public final class StringUtils {

    private static final Pattern ACENTOS = Pattern.compile("\\p{M}");
    private static final Pattern NAO_NUMEROS = Pattern.compile("[^0-9]");

    private StringUtils() {
    }

    public static String removerAcentos(String texto) {
        if (texto == null) {
            return null;
        }
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return ACENTOS.matcher(normalizado).replaceAll("");
    }

    public static String apenasNumeros(String texto) {
        if (texto == null) {
            return null;
        }
        return NAO_NUMEROS.matcher(texto).replaceAll("");
    }

    public static boolean isNullOrBlank(String texto) {
        return texto == null || texto.isBlank();
    }

    public static boolean isNotBlank(String texto) {
        return texto != null && !texto.isBlank();
    }

    public static String truncar(String texto, int tamanhoMaximo) {
        if (texto == null || texto.length() <= tamanhoMaximo) {
            return texto;
        }
        return texto.substring(0, tamanhoMaximo);
    }

    public static String primeiraLetraMaiuscula(String texto) {
        if (isNullOrBlank(texto)) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}
