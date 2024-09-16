package org.neoflex.vacationcalculator.controller;

import org.neoflex.vacationcalculator.model.VacationResponse;
import org.neoflex.vacationcalculator.service.VacationCalculatorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;

    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<VacationResponse> calculateVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) List<LocalDate> vacationDates
    ) {
        double vacationPay = vacationCalculatorService.calculateVacationPay(averageSalary, vacationDays, vacationDates);
        return ResponseEntity.ok(new VacationResponse(vacationPay));
    }
}