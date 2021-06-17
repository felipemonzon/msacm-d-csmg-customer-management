package com.rinku.csmg.customer.management.business;

import com.rinku.csmg.customer.management.entity.EmployeeEntity;
import com.rinku.csmg.customer.management.entity.RolEntity;
import com.rinku.csmg.customer.management.exception.custom.EmployeeExist;
import com.rinku.csmg.customer.management.exception.custom.NoDataFoundException;
import com.rinku.csmg.customer.management.model.EmployeeRequest;
import com.rinku.csmg.customer.management.repository.EmployeeRepository;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

/**
 * Test de la clase de negocio.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeBusinessTest {
  /**
   * clase a testear.
   */
  @InjectMocks
  private EmployeeBusiness employeeBusiness;
  /**
   * Servicio de empleados.
   */
  @Mock
  private EmployeeRepository employeeRepository;

  /**
   * Inicializa los componentes de mockito.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Consulta empleados de rinku.
   * 
   * @when consulta los empleados
   * @given
   * @then lista de todos los empleados
   */
  @Test
  public void getEmployeesTest() {
    Mockito.when(this.employeeRepository.findAll())
        .thenReturn(Arrays.asList(this.getEmployeeEntity()));
    Assert.assertNotNull(this.employeeBusiness.getEmployees());
  }

  /**
   * Consulta empleados de rinku.
   * 
   * @when consulta una pagina de empleados
   * @given parametros de paginacion
   * @then lista paginada los empleados
   */
  @Test
  public void paginationTest() {
    Page<EmployeeEntity> pagedResponse =
        new PageImpl<>(Arrays.asList(this.getEmployeeEntity()), PageRequest.of(1, 1), 400L);
    Mockito.when(this.employeeRepository.findAll(Mockito.any(Pageable.class)))
        .thenReturn(pagedResponse);
    Assert.assertNotNull(this.employeeBusiness.getEmployees(PageRequest.of(1, 1)));
  }

  /**
   * Consulta empleados de rinku.
   * 
   * @when consulta una pagina de empleados
   * @given parametros de paginacion
   * @then lista paginada los empleados
   */
  @Test
  public void paginationInitTest() {
    Page<EmployeeEntity> pagedResponse =
        new PageImpl<>(Arrays.asList(this.getEmployeeEntity()), PageRequest.of(1, 1), 400L);
    Mockito.when(this.employeeRepository.findAll(Mockito.any(Pageable.class)))
        .thenReturn(pagedResponse);
    Assert.assertNotNull(this.employeeBusiness.getEmployees(PageRequest.of(0, 1)));
  }

  /**
   * Consulta empleados de rinku.
   * 
   * @when consulta una pagina vacia de empleados
   * @given parametros de paginacion
   * @then lista vacia paginada los empleados
   */
  @Test
  public void paginationErrorTest() {
    Throwable e = null;
    try {
      this.employeeBusiness.getEmployees(PageRequest.of(1, 1));
    } catch (Exception ex) {
      e = ex;
    }
    Assert.assertTrue(e instanceof NullPointerException);
  }

  /**
   * Consulta un empleado de rinku.
   * 
   * @when consulta un empleado que no existe
   * @given parametros de busqueda
   * @then empleado encontrado
   */
  @Test
  public void searchByTest() {
    Mockito
        .when(this.employeeRepository.findByEmployeeNumberOrFirstnameOrLastanmeIgnoreCase(
            Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Arrays.asList(this.getEmployeeEntity()));
    Assert.assertNotNull(this.employeeBusiness.searchBy(StringUtils.EMPTY));
  }

  /**
   * Consulta un empleado de rinku.
   * 
   * @when consulta un empleado que no existe
   * @given parametros de busqueda
   * @then empleado no encontrado
   */
  @Test
  public void searchByErrorTest() {
    Throwable e = null;
    try {
      this.employeeBusiness.searchBy(StringUtils.EMPTY);
    } catch (Exception ex) {
      e = ex;
    }
    Assert.assertTrue(e instanceof NoDataFoundException);
  }

  /**
   * Guarda un empleado.
   * 
   * @when guarda un empleado con exito
   * @given datos de empleado a guardar
   * @then empleado guardado con exito
   */
  @Test
  public void saveEmployeeTest() {
    Mockito.when(this.employeeRepository.save(Mockito.any())).thenReturn(new EmployeeEntity());
    this.employeeBusiness.save(this.getEmployeeRequest());
    Assert.assertTrue(true);
  }

  /**
   * Guarda un empleado que ya existe.
   * 
   * @when intenta guardar un empleado
   * @given datos de empleado a guardar
   * @then empleado guardado con exito
   */
  @Test
  public void saveEmployeeExistTest() {
    Throwable e = null;
    try {
      Mockito.when(this.employeeRepository.findByFirstnameAndLastanme(Mockito.anyString(),
          Mockito.anyString())).thenReturn(new EmployeeEntity());
      this.employeeBusiness.save(this.getEmployeeRequest());
    } catch (Exception ex) {
      e = ex;
    }
    Assert.assertTrue(e instanceof EmployeeExist);
  }

  /**
   * Actualiza un empleado.
   * 
   * @when intenta actualizar un empleado con exito
   * @given datos de empleado a actualizar
   * @then error 404 dato no encontrado
   */
  @Test
  public void updateEmployeeErrorTest() {
    Throwable e = null;
    try {
      this.employeeBusiness.replaceEmployee(new EmployeeRequest(), 1L);
    } catch (Exception ex) {
      e = ex;
    }
    Assert.assertTrue(e instanceof NoDataFoundException);
  }

  /**
   * Actualiza un empleado.
   * 
   * @when actualiza un empleado con exito
   * @given datos de empleado a actualizar
   * @then empleado guardado con exito
   */
  @Test
  public void updateEmployeeTest() {
    Mockito.when(this.employeeRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(this.getEmployeeEntity()));
    Mockito.when(this.employeeRepository.save(Mockito.any())).thenReturn(new EmployeeEntity());
    this.employeeBusiness.replaceEmployee(new EmployeeRequest(), 1L);
    Assert.assertTrue(true);
  }

  /**
   * Obtiene un objeto entity.
   * 
   * @return una entidad de empleado
   */
  private EmployeeEntity getEmployeeEntity() {
    EmployeeEntity entity = new EmployeeEntity();
    entity.setFirstname(StringUtils.EMPTY);
    entity.setLastanme(StringUtils.EMPTY);
    entity.setEmployeeNumber(StringUtils.EMPTY);
    entity.setIdEmployee(1L);
    entity.setRol(new RolEntity());
    return entity;
  }

  /**
   * Obtiene un objeto request.
   * 
   * @return una request de empleado
   */
  private EmployeeRequest getEmployeeRequest() {
    EmployeeRequest entity = new EmployeeRequest();
    entity.setFirstname("juan");
    entity.setLastanme("lopez");
    entity.setIdEmployee(1L);
    entity.setIdRol(1);
    return entity;
  }
}
