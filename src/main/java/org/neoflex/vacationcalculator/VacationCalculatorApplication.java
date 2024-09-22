package org.neoflex.vacationcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска Spring Boot приложения.
 */
@SpringBootApplication
public class VacationCalculatorApplication {

    public static void main(String[] args) {
        // Запуск Spring Boot приложения
        SpringApplication.run(VacationCalculatorApplication.class, args);
    }
}
