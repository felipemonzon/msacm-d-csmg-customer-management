package com.rinku.csmg.customer.management.utilities;

import com.rinku.csmg.customer.management.constant.ApiConstant;
import com.rinku.csmg.customer.management.entity.EmployeeEntity;
import com.rinku.csmg.customer.management.model.Employee;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * Utilidades para el servicio de nomina de rinku.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
public abstract class Utilities {
  /**
   * Formato de salida de la respuesta de error.
   */
  public static final String ERROR_DATE_PATTER = "yyyy-MM-dd HH:mm:ss";
  /**
   * Formato para el random.
   */
  private static final String RANDOM_PATTERN = "%04d";
  /**
   * Numero maximo para generar random.
   */
  private static final int MAXIMUM_RANDOM = 10000;

  /**
   * Obtiene la pagina actual.
   *
   * @param pageable datos de paginación
   * @return pagina actual
   */
  public static int getCurrentPage(Pageable pageable) {
    int page = pageable.getPageNumber();
    if (pageable.getPageNumber() != ApiConstant.INT_ZERO_VALUE) {
      page -= ApiConstant.INT_ONE_VALUE;
    }
    return page;
  }

  /**
   * Convertidor para inicializar los bigdecimal en cero si estos son nulos.
   * 
   * @return propiedad con su valor asignado de base de datos o cero si este se encontraba nulo
   */
  private static Converter<BigDecimal, BigDecimal> initialBigDecimal() {
    return c -> Objects.nonNull(c.getSource()) ? c.getSource() : BigDecimal.ZERO;
  }

  /**
   * Convertidor de datos.
   * 
   * @return propiedad con su valor asignado
   */
  private static Converter<String, String> toLowerCase() {
    return c -> Objects.nonNull(c.getSource()) ? c.getSource().toLowerCase() : StringUtils.EMPTY;
  }

  /**
   * Método de configuración de modelMapper
   * 
   * @return instancia configurada de ModelMapper.
   */
  public static ModelMapper mapperForEmployee() {
    ModelMapper mapper = new ModelMapper();
    mapper.createTypeMap(EmployeeEntity.class, Employee.class)
        .addMappings(m -> m.using(Utilities.initialBigDecimal()).map(em -> em.getRol().getSalary(),
            Employee::setSalary))
        .addMappings(m -> m.using(Utilities.toLowerCase()).map(em -> em.getRol().getDescription(),
            Employee::setRolDescription));
    return mapper;
  }

  /**
   * Método de configuración de modelMapper
   * 
   * @return instancia configurada de ModelMapper.
   */
  public static ModelMapper mapperForEmployeeEntity() {
    ModelMapper mapper = new ModelMapper();
    mapper.createTypeMap(Employee.class, EmployeeEntity.class)
        .addMappings(m -> m.using(Utilities.toLowerCase()).map(Employee::getEmployeeNumber,
            EmployeeEntity::setEmployeeNumber));
    return mapper;
  }

  /**
   * Genera el identificador del empleado
   * 
   * @param firstname nombre del empleado
   * @param lastname apellido del empleado
   * @return numero de empleado
   */
  public static String generateEmployeeNumber(String firstname, String lastname) {
    SecureRandom ranGen = new SecureRandom();
    return lastname.substring(0, 2) + firstname.substring(0, 2)
        + String.format(RANDOM_PATTERN, ranGen.nextInt(MAXIMUM_RANDOM));
  }

  /**
   * Constructor de la clase.
   */
  private Utilities() {}
}
