package org.neoflex.vacationcalculator.controller;

import org.neoflex.vacationcalculator.model.VacationResponse;
import org.neoflex.vacationcalculator.service.VacationCalculatorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST-контроллер для обработки запросов на расчет отпускных.
 */
@RestController
@RequestMapping("/api/v1")
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;

    /**
     * Конструктор для инжекции сервиса расчета отпускных.
     *
     * @param vacationCalculatorService Сервис расчета отпускных
     */
    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }

    /**
     * Эндпоинт для расчета отпускных выплат.
     * Обрабатывает GET-запрос по пути /api/v1/calculate.
     *
     * @param averageSalary Средняя зарплата за месяц (обязательный параметр)
     * @param vacationDays   Количество дней отпуска (обязательный параметр)
     * @param vacationDates  Список дат отпуска в формате ISO (yyyy-MM-dd) (необязательный параметр)
     * @return Объект VacationResponse с рассчитанной суммой отпускных в формате JSON
     */
    @GetMapping("/calculate")
    public ResponseEntity<VacationResponse> calculateVacationPay(
            @RequestParam double averageSalary, // Средняя месячная зарплата
            @RequestParam int vacationDays,     // Количество отпускных дней
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) List<LocalDate> vacationDates // Список дат отпуска в формате ISO
    ) {
        // Вызываем сервис для расчета отпускных выплат
        double vacationPay = vacationCalculatorService.calculateVacationPay(averageSalary, vacationDays, vacationDates);

        // Возвращаем результат в виде HTTP-ответа с кодом 200 OK и данными в формате JSON
        return ResponseEntity.ok(new VacationResponse(vacationPay));
    }
}
