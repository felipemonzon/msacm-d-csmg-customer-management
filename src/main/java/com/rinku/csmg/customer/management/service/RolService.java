package com.rinku.csmg.customer.management.service;

import com.rinku.csmg.customer.management.model.Rol;

import java.util.List;

/**
 * Servicio para roles de empleado.
 * 
 * @author Felipe Monzón
 * @date 15 jun. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public interface RolService {
  /**
   * Consulta todos los roles.
   * 
   * @return una lista de roles
   */
  List<Rol> getRoles();
}
