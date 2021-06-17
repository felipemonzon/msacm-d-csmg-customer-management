package com.rinku.csmg.customer.management.entity;

import com.rinku.csmg.customer.management.constant.DatabaseConstant;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity by employee.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@Data
@Entity
@Table(name = DatabaseConstant.EMPLOYEES_TABLE)
public class EmployeeEntity {
  /**
   * Principal id for entity, generate for sequence or identity.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DatabaseConstant.EMPLOYEE_ID_FIELD, nullable = false)
  private Long idEmployee;
  /**
   * Matricula del empleado.
   */
  @Column(name = DatabaseConstant.EMPLOYEE_NUMBER_FIELD, nullable = false, updatable = false,
      unique = true, length = 50)
  private String employeeNumber;
  /**
   * Employee's firstname.
   */
  @Column(name = DatabaseConstant.EMPLOYEE_FIRSTNAME_FIELD, nullable = false, length = 50)
  private String firstname;
  /**
   * Employee's lastname.
   */
  @Column(name = DatabaseConstant.EMPLOYEE_LASTNAME_FIELD, nullable = false, length = 50)
  private String lastanme;
  /**
   * Rol del empleado.
   */
  @ManyToOne
  @JoinColumn(name = DatabaseConstant.ROL_FIELD,
      referencedColumnName = DatabaseConstant.ID_ROL_FIELD)
  private RolEntity rol;
  /**
   * Tipo de empleado.
   */
  @ManyToOne
  @JoinColumn(name = DatabaseConstant.EMPLOYEE_TYPE_FIELD,
      referencedColumnName = DatabaseConstant.ID_TYPE_FIELD)
  private EmployeeType employeeType;
  /**
   * Estatus del empleado.
   */
  @ManyToOne
  @JoinColumn(name = DatabaseConstant.EMPLOYEE_STATUS_FIELD,
      referencedColumnName = DatabaseConstant.ID_STATUS_FIELD)
  private StatusEmployee status;
}
