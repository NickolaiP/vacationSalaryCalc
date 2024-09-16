package org.neoflex.vacationcalculator;

import org.neoflex.vacationcalculator.service.VacationCalculatorService;
import org.neoflex.vacationcalculator.utils.HolidayUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VacationCalculatorServiceTest {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @MockBean
    private HolidayUtils holidayUtils;

    @Test
    public void testCalculateVacationPayWithoutDates() {
        double averageSalary = 60000;
        int vacationDays = 10;
        double expectedVacationPay = (60000 / 29.3) * 10;

        double actualVacationPay = vacationCalculatorService.calculateVacationPay(averageSalary, vacationDays, null);

        assertEquals(expectedVacationPay, actualVacationPay, 0.01);
    }

    @Test
    public void testCalculateVacationPayWithDates() {
        double averageSalary = 60000;
        List<LocalDate> vacationDates = Arrays.asList(
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 12)
        );

        Mockito.when(holidayUtils.calculateEffectiveVacationDays(vacationDates)).thenReturn(2);

        double expectedVacationPay = (60000 / 29.3) * 2;
        double actualVacationPay = vacationCalculatorService.calculateVacationPay(averageSalary, 0, vacationDates);

        assertEquals(expectedVacationPay, actualVacationPay, 0.01);
    }
}