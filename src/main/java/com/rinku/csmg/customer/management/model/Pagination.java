package com.rinku.csmg.customer.management.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Objeto de paginación.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Pagination {
  /**
   * Página actual.
   */
  private int currentPage;
  /**
   * Páginas totales.
   */
  private int totalPages;
  /**
   * Página final.
   */
  private boolean lastPage;
  /**
   * Página inicial.
   */
  private boolean firstPage;
}
