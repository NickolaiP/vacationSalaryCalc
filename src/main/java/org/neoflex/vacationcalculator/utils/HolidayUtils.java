package org.neoflex.vacationcalculator.utils;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Утилитный класс для работы с праздничными и выходными днями.
 */
@Component
public class HolidayUtils {

    // Список праздничных дней
    private static final List<LocalDate> HOLIDAYS = Arrays.asList(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 6, 12),
            LocalDate.of(2024, 11, 4)
    );

    /**
     * Рассчитывает количество фактических отпускных дней, исключая праздники и выходные.
     * @param vacationDates Список дат отпуска
     * @return Количество рабочих дней в отпуске
     */
    public int calculateEffectiveVacationDays(List<LocalDate> vacationDates) {
        // Фильтруем даты, исключая выходные и праздники, и считаем количество рабочих дней
        return (int) vacationDates.stream()
                .filter(date -> !isHolidayOrWeekend(date))
                .count();
    }

    /**
     * Проверяет, является ли дата праздником или выходным днем.
     * @param date Дата для проверки
     * @return true, если дата - праздник или выходной, иначе false
     */
    private boolean isHolidayOrWeekend(LocalDate date) {
        return HOLIDAYS.contains(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
