package com.rinku.csmg.customer.management.business;

import com.rinku.csmg.customer.management.entity.RolEntity;
import com.rinku.csmg.customer.management.repository.RolRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

/**
 * Pruebas de la clase de negocio.
 * 
 * @author Felipe Monzón
 * @date 15 jun. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
public class RolBusinessTest {
  /**
   * clase a testear.
   */
  @InjectMocks
  private RolBusiness rolBusiness;
  /**
   * Servicio de roles de empleados.
   */
  @Mock
  private RolRepository rolRepository;

  /**
   * Inicializa los componentes de mockito.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Consulta los roles de los empleados de rinku.
   * 
   * @when consulta los roles de los empleados
   * @given
   * @then lista de todos los roles de empleados
   */
  @Test
  public void getRolesTest() {
    Mockito.when(this.rolRepository.findAll()).thenReturn(Arrays.asList(new RolEntity()));
    Assert.assertNotNull(this.rolBusiness.getRoles());
  }

}
