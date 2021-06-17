package com.rinku.csmg.customer.management.constant;

/**
 * Constantes para el log.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public abstract class LogConstant {
  /**
   * Log para la entrada de las peticiones.
   */
  public static final String REQUEST = "Request {}";
  /**
   * Log para mensaje de 404.
   */
  public static final String NO_DATA = "Dato no Encontrado.";
  /**
   * Log para consultar los empleados.
   */
  public static final String EMPLOYEES_RETREVE = "Consulta de Empleados";
  /**
   * Log para consultar los empleados.
   */
  public static final String EMPLOYEES_PAGINATION = "Consulta de Empleados, pagina {}";
  /**
   * Log para los datos obtenidos de base de datos.
   */
  public static final String DATABASE_DATA = "Datos recuperados de base de datos {}";
  /**
   * Log para consultar un empleado por una opcion.
   */
  public static final String EMPLOYEE_SEARCH = "Consulta el empleado por {}";
  /**
   * Log para empleado existente.
   */
  public static final String EMPLOYEE_EXIST = "Empleado {} {} ya existe.";

  /**
   * Constructor de la clase.
   */
  private LogConstant() {}
}
