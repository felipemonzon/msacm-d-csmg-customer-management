package com.rinku.csmg.customer.management.repository;

import com.rinku.csmg.customer.management.entity.RolEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para los roles.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public interface RolRepository extends JpaRepository<RolEntity, Long> {

}
