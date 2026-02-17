package com.exemplo.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilitarios para manipulacao de datas.
 * 
 * ONDE FICA: common/util/
 * 
 * CARACTERISTICAS:
 * - Classe final (nao extensivel)
 * - Construtor privado (nao instanciavel)
 * - Metodos estaticos
 * - Null-safe
 */
public final class DateUtils {

    private static final DateTimeFormatter FORMATO_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_BR_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter FORMATO_ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    private DateUtils() {
    }

    public static String formatarDataBr(LocalDate data) {
        if (data == null) {
            return null;
        }
        return data.format(FORMATO_BR);
    }

    public static String formatarDataHoraBr(LocalDateTime dataHora) {
        if (dataHora == null) {
            return null;
        }
        return dataHora.format(FORMATO_BR_HORA);
    }

    public static LocalDate parseDataBr(String dataStr) {
        if (dataStr == null || dataStr.isBlank()) {
            return null;
        }
        return LocalDate.parse(dataStr, FORMATO_BR);
    }

    public static LocalDate parseDataIso(String dataStr) {
        if (dataStr == null || dataStr.isBlank()) {
            return null;
        }
        return LocalDate.parse(dataStr, FORMATO_ISO);
    }

    public static boolean isDataPassada(LocalDate data) {
        if (data == null) {
            return false;
        }
        return data.isBefore(LocalDate.now());
    }

    public static boolean isDataFutura(LocalDate data) {
        if (data == null) {
            return false;
        }
        return data.isAfter(LocalDate.now());
    }
}
