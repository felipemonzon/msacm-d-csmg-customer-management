package com.rinku.csmg.customer.management.exception.custom;

/**
 * Excepcion lanzada para un usuario existente.
 * 
 * @author Felipe Monzón
 * @date 14 jun. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public class EmployeeExist extends RuntimeException {
  /**
   * UID auto generado para el versionado de la clase.
   */
  private static final long serialVersionUID = -1182364561358769982L;

  /**
   * Constructor de la clase.
   */
  public EmployeeExist() {}

  /**
   * Constructor de la clase.
   *
   * @param message mensaje de excepción arrojada por bad request.
   */
  public EmployeeExist(String message) {
    super(message);
  }
}
