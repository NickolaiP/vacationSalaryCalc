package org.neoflex.vacationcalculator.service;

import org.neoflex.vacationcalculator.utils.HolidayUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacationCalculatorService {

    private final HolidayUtils holidayUtils;

    public VacationCalculatorService(HolidayUtils holidayUtils) {
        this.holidayUtils = holidayUtils;
    }

    public double calculateVacationPay(double averageSalary, int vacationDays, List<LocalDate> vacationDates) {
        double dailyRate = averageSalary / 29.3;

        if (vacationDates != null && !vacationDates.isEmpty()) {
            vacationDays = holidayUtils.calculateEffectiveVacationDays(vacationDates);
        }

        return dailyRate * vacationDays;
    }
}