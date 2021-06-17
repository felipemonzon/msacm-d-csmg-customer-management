package com.rinku.csmg.customer.management.constant;

/**
 * Constantes de rutas.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public abstract class RoutesConstant {
  /**
   * Ruta base del proyecto.
   */
  public static final String BASE_PATH = "${api.uri.basePath}";
  /**
   * Ruta para la consulta de empleados.
   */
  public static final String EMPLOYEE_RETRIEVE_PATH = "${api.uri.employees.retrieve.mapping}";
  /**
   * Ruta para el guardado de empleados.
   */
  public static final String EMPLOYEE_SAVE_PATH = "${api.uri.employees.save.mapping}";
  /**
   * Ruta para la actualización de empleados.
   */
  public static final String EMPLOYEE_UPDATE_PATH = "${api.uri.employees.update.mapping}";
  /**
   * Ruta para la páginacion de empleados.
   */
  public static final String EMPLOYEE_PAGINATION_PATH =
      "${api.uri.employees.retrieve.pagintation.mapping}";
  /**
   * Ruta para consultar un empleado por una opcion.
   */
  public static final String EMPLOYEE_SEARCH_PATH = "${api.uri.employees.search.mapping}";
  /**
   * Ruta para consultar todos los roles de los empleado.
   */
  public static final String ROLES_RETRIEVE_PATH = "${api.uri.roles.retrieve.mapping}";

  /**
   * Constructor de la clase.
   */
  private RoutesConstant() {}
}
