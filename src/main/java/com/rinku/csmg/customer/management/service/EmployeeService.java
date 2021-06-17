package com.rinku.csmg.customer.management.service;

import com.rinku.csmg.customer.management.model.Employee;
import com.rinku.csmg.customer.management.model.EmployeeRequest;
import com.rinku.csmg.customer.management.model.EmployeeResponse;
import com.rinku.csmg.customer.management.model.EmployeeSaveResponse;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Servicio para empleados.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public interface EmployeeService {
  /**
   * Consulta todos los empleados.
   * 
   * @return una lista de {@code Employee}
   */
  List<Employee> getEmployees();

  /**
   * Consulta una lista páginada de los empleados.
   * 
   * @return una lista páginada de tipo {@code Employee}
   */
  EmployeeResponse getEmployees(Pageable pageable);

  /**
   * Guarda un empleado.
   * 
   * @param request objeto de tipo {@code EmployeeRequest} return {@code EmployeeSaveResponse}
   */
  EmployeeSaveResponse save(EmployeeRequest request);

  /**
   * Actualiza un empleado.
   * 
   * @param request objeto {@code EmployeeRequest}
   * @param id identificador de empleado
   */
  void replaceEmployee(EmployeeRequest request, long id);

  /**
   * Consulta un empleado por una opcion.
   * 
   * @param search parametro a buscar
   * @return una lista de tipo {@code Employee}
   */
  List<Employee> searchBy(String search);
}
