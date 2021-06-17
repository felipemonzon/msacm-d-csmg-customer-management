package com.rinku.csmg.customer.management.api;

import com.rinku.csmg.customer.management.model.Employee;
import com.rinku.csmg.customer.management.model.EmployeeRequest;
import com.rinku.csmg.customer.management.service.EmployeeService;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Test del controlador.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {
  /**
   * clase a testear.
   */
  @InjectMocks
  private EmployeeController employeeController;
  /**
   * Servicio de empleados.
   */
  @Mock
  private EmployeeService employeeService;

  /**
   * Inicializa los componentes de mockito.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Consulta todos los empleados
   * 
   * @when consulta todos los empleados
   * @given headers and request
   * @then una lista de empleados
   */
  @Test
  public void employeeRetrieveTest() {
    Mockito.when(this.employeeService.getEmployees()).thenReturn(Arrays.asList(new Employee()));
    Assert.assertNotNull(this.employeeController.retrieve(new HttpHeaders()));
  }

  /**
   * Consulta un empleado
   * 
   * @when consulta un empleado
   * @given headers and request
   * @then el empleado encontrado
   */
  @Test
  public void employeeSearchByTest() {
    Mockito.when(this.employeeService.searchBy(Mockito.anyString()))
        .thenReturn(Arrays.asList(new Employee()));
    Assert.assertNotNull(this.employeeController.searchBy(new HttpHeaders(), StringUtils.EMPTY));
  }

  /**
   * Consulta todos los empleados
   * 
   * @when consulta todos los empleados por pagina
   * @given headers and request
   * @then una lista paginada de empleados
   */
  @Test
  public void employeePaginationTest() {
    Mockito.when(this.employeeService.getEmployees()).thenReturn(Arrays.asList(new Employee()));
    Assert.assertNotNull(this.employeeController.retrieve(PageRequest.of(0, 1)));
  }

  /**
   * Guarda un empleado
   * 
   * @when Guarda un empleado
   * @given headers and request
   * @then httpStatus 201
   */
  @Test
  public void employeeSaveTest() {
    Assert.assertNotNull(
        this.employeeController.saveEmployee(new HttpHeaders(), new EmployeeRequest()));
  }

  /**
   * Actualiza un empleado
   * 
   * @when Actualiza un empleado
   * @given headers and request
   * @then httpStatus 204
   */
  @Test
  public void employeeUpdateTest() {
    Assert.assertNotNull(
        this.employeeController.updateEmployee(new HttpHeaders(), new EmployeeRequest(), 1));
  }
}
