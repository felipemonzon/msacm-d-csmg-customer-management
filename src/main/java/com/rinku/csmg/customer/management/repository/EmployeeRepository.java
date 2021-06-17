package com.rinku.csmg.customer.management.repository;

import com.rinku.csmg.customer.management.entity.EmployeeEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para empleados.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
  /**
   * Consulta un empleado por su matricula.
   * 
   * @param employeeNumber matricula del empleado
   * @param firstname nombre del empleado
   * @param lastname apellido del empleado
   * @return una lista de {@code EmployeeEntity}
   */
  List<EmployeeEntity> findByEmployeeNumberOrFirstnameOrLastanmeIgnoreCase(
      final String employeeNumber, final String firstname, final String lastname);

  /**
   * Consulta un empleado por nombre y apellido.
   * 
   * @param firsname primer nombre
   * @param lastname apellido del empleado
   * @return empleado encontrado
   */
  EmployeeEntity findByFirstnameAndLastanme(final String firsname, final String lastname);
}
