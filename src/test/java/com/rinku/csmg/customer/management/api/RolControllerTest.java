package com.rinku.csmg.customer.management.api;

import com.rinku.csmg.customer.management.model.Rol;
import com.rinku.csmg.customer.management.service.RolService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Test del controlador.
 * 
 * @author Felipe Monzón
 * @since 15 jun. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RolControllerTest {
  /**
   * clase a testear.
   */
  @InjectMocks
  private RolController rolController;
  /**
   * Servicio de roles.
   */
  @Mock
  private RolService rolService;

  /**
   * Inicializa los componentes de mockito.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Consulta todos los roles de los empleados
   * 
   * @when consulta todos los roles de los empleados
   * @given headers and request
   * @then una lista de roles
   */
  @Test
  public void employeeRetrieveTest() {
    Mockito.when(this.rolService.getRoles()).thenReturn(Arrays.asList(new Rol()));
    Assert.assertNotNull(this.rolController.retrieve(new HttpHeaders()));
  }
}
