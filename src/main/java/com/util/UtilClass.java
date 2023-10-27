package com.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class UtilClass {
    private UtilClass() {}
    static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static LocalDate parseDate(String targetDateStr) {
        DateTimeFormatter formatter = getFormatter();
        LocalDate targetDate = LocalDate.parse(targetDateStr, formatter);
        return targetDate;
    }
}
