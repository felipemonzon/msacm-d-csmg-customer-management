package com.rinku.csmg.customer.management.entity;

import com.rinku.csmg.customer.management.constant.DatabaseConstant;

import lombok.Data;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Status del empleado.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@Data
@Entity
@Table(name = DatabaseConstant.EMPLOYEE_STATUS_TABLE)
public class StatusEmployee {
  /**
   * Principal id for entity, generate for sequence or identity.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DatabaseConstant.ID_STATUS_FIELD, nullable = false, updatable = false,
      unique = true)
  private Long idStatus;
  /**
   * Property for description.
   */
  @NaturalId
  @Column(name = DatabaseConstant.DESCRIPTION_FIELD, nullable = false, unique = true, length = 50)
  private String description;
}
