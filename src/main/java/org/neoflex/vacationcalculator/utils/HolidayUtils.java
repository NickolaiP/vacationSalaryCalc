package org.neoflex.vacationcalculator.utils;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class HolidayUtils {

    private static final List<LocalDate> HOLIDAYS = Arrays.asList(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9)
            // Добавь больше праздников здесь
    );

    public int calculateEffectiveVacationDays(List<LocalDate> vacationDates) {
        return (int) vacationDates.stream()
                .filter(date -> !isHolidayOrWeekend(date))
                .count();
    }

    private boolean isHolidayOrWeekend(LocalDate date) {
        return HOLIDAYS.contains(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}