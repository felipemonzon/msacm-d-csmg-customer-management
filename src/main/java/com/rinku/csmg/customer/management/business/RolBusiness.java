package com.rinku.csmg.customer.management.business;

import com.rinku.csmg.customer.management.entity.RolEntity;
import com.rinku.csmg.customer.management.model.Rol;
import com.rinku.csmg.customer.management.repository.RolRepository;
import com.rinku.csmg.customer.management.service.RolService;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para lod roles de empleados.
 * 
 * @author Felipe Monzón
 * @since 15 jun. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RolBusiness implements RolService {
  /**
   * Repositorio de roles.
   */
  private final RolRepository rolRepository;

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<Rol> getRoles() {
    return this.rolRepository.findAll().stream().map(this::mappingRol).collect(Collectors.toList());
  }

  /**
   * Mapea una clase de tipo entity a una clase de DTO.
   * 
   * @param entity clase obtenida de base de datos
   * @return un objeto de tipo {@link Rol}
   */
  private Rol mappingRol(RolEntity entity) {
    return new ModelMapper().map(entity, Rol.class);
  }
}
