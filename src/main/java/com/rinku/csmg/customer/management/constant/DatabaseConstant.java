package com.rinku.csmg.customer.management.constant;

/**
 * Constantes para la base de datos.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public abstract class DatabaseConstant {
  /**
   * Propiedad para el campo "create_user".
   */
  public static final String CREATE_USER = "create_user";
  /**
   * Property for table field "create_date".
   */
  public static final String CREATE_DATE = "create_date";
  /**
   * Property for table field "lastmodified_user".
   */
  public static final String LAST_MODIFIED_USER = "lastmodified_user";
  /**
   * Property for table field "lastmodified_date".
   */
  public static final String LAST_MODIFIED_DATE = "lastmodified_date";
  /**
   * Property for table name "roles".
   */
  public static final String ROLES_TABLE = "roles";
  /**
   * Property for table field "id_role".
   */
  public static final String ID_ROL_FIELD = "id_rol";
  /**
   * Property for table field "description".
   */
  public static final String DESCRIPTION_FIELD = "description";
  /**
   * Property for table name "employee_types".
   */
  public static final String EMPLOYEE_TYPE_TABLE = "employee_types";
  /**
   * Property for table field "id_type".
   */
  public static final String ID_TYPE_FIELD = "id_type";
  /**
   * Property for table name "employees".
   */
  public static final String EMPLOYEES_TABLE = "employees";
  /**
   * Property for table field "id".
   */
  public static final String EMPLOYEE_ID_FIELD = "id_employee";
  /**
   * Property for table field "firstname".
   */
  public static final String EMPLOYEE_FIRSTNAME_FIELD = "firstname";
  /**
   * Property for table field "lastname".
   */
  public static final String EMPLOYEE_LASTNAME_FIELD = "lastname";
  /**
   * Property for table field "employee_number".
   */
  public static final String EMPLOYEE_NUMBER_FIELD = "employee_number";
  /**
   * Property for table field "rol".
   */
  public static final String ROL_FIELD = "rol";
  /**
   * Property for table field "employee_type".
   */
  public static final String EMPLOYEE_TYPE_FIELD = "employee_type";
  /**
   * Property for table name "employee_status".
   */
  public static final String EMPLOYEE_STATUS_TABLE = "employee_status";
  /**
   * Property for table field "id_status".
   */
  public static final String ID_STATUS_FIELD = "id_status";
  /**
   * Property for table field "status".
   */
  public static final String EMPLOYEE_STATUS_FIELD = "status";
  /**
   * Property for table filed "salary".
   */
  public static final String SALARY_FIELD = "salary";

  /**
   * Private constructor to avoid initializations .
   */
  private DatabaseConstant() {}
}
