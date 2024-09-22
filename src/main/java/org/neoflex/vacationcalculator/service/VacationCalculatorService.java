package org.neoflex.vacationcalculator.service;

import org.neoflex.vacationcalculator.utils.HolidayUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для расчета отпускных выплат.
 */
@Service
public class VacationCalculatorService {

    private final HolidayUtils holidayUtils; // Сервис для работы с праздниками и выходными

    /**
     * Конструктор, в который инжектируется утилита HolidayUtils.
     * @param holidayUtils Утилита для работы с праздничными днями
     */
    public VacationCalculatorService(HolidayUtils holidayUtils) {
        this.holidayUtils = holidayUtils;
    }

    /**
     * Рассчитывает отпускные выплаты, исходя из средней зарплаты, количества дней отпуска и списка дат.
     * @param averageSalary Средняя месячная зарплата
     * @param vacationDays Количество дней отпуска
     * @param vacationDates Список дат отпуска
     * @return Сумма отпускных выплат
     */
    public double calculateVacationPay(double averageSalary, int vacationDays, List<LocalDate> vacationDates) {
        // Рассчитываем дневную ставку, исходя из средней зарплаты
        double dailyRate = averageSalary / 29.3;

        // Если даты отпуска переданы, пересчитываем количество отпускных дней, исключая праздники и выходные
        if (vacationDates != null && !vacationDates.isEmpty()) {
            vacationDays = holidayUtils.calculateEffectiveVacationDays(vacationDates);
        }

        // Возвращаем расчет отпускных
        return dailyRate * vacationDays;
    }
}
