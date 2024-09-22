package org.neoflex.vacationcalculator.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс-ответ для представления суммы отпускных.
 */
@Setter
@Getter
public class VacationResponse {
    private double vacationPay; // Поле для хранения суммы отпускных

    /**
     * Конструктор для установки суммы отпускных.
     * @param vacationPay Сумма отпускных
     */
    public VacationResponse(double vacationPay) {
        this.vacationPay = vacationPay;
    }
}
