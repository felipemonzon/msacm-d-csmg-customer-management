package com.rinku.csmg.customer.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Respuesta con el numero de empleado.
 * 
 * @author Felipe Monzón
 * @date 14 jun. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveResponse {
  /**
   * Numero de empleado.
   */
  private String employeeNumber;
}
