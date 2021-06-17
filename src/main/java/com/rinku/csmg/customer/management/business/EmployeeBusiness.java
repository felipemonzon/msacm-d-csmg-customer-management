package com.rinku.csmg.customer.management.business;

import com.rinku.csmg.customer.management.constant.LogConstant;
import com.rinku.csmg.customer.management.entity.EmployeeEntity;
import com.rinku.csmg.customer.management.exception.custom.EmployeeExist;
import com.rinku.csmg.customer.management.exception.custom.NoDataFoundException;
import com.rinku.csmg.customer.management.model.Employee;
import com.rinku.csmg.customer.management.model.EmployeeRequest;
import com.rinku.csmg.customer.management.model.EmployeeResponse;
import com.rinku.csmg.customer.management.model.EmployeeSaveResponse;
import com.rinku.csmg.customer.management.repository.EmployeeRepository;
import com.rinku.csmg.customer.management.service.EmployeeService;
import com.rinku.csmg.customer.management.utilities.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para empleados.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeBusiness implements EmployeeService {
  /**
   * Repositorio de empleados.
   */
  private final EmployeeRepository employeeRepository;

  /**
   * {@inheritDoc}.
   */
  @Override
  @Transactional(readOnly = true)
  public List<Employee> getEmployees() {
    log.info(LogConstant.EMPLOYEES_RETREVE);
    return this.employeeRepository.findAll().stream().map(this::mappingEmployee)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  @Transactional(readOnly = true)
  public EmployeeResponse getEmployees(Pageable pageable) {
    int currentPage = Utilities.getCurrentPage(pageable);
    log.info(LogConstant.EMPLOYEES_PAGINATION, currentPage);
    Page<Employee> employees = this.employeeRepository
        .findAll(PageRequest.of(currentPage, pageable.getPageSize())).map(this::mappingEmployee);
    return EmployeeResponse.builder().employees(employees.getContent())
        .currentPage(pageable.getPageNumber()).firstPage(employees.isFirst())
        .lastPage(employees.isLast()).totalPages(employees.getTotalPages()).build();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  @Transactional
  public EmployeeSaveResponse save(EmployeeRequest request) {
    log.debug(LogConstant.REQUEST, request.toString());
    String employeeNumber = this.validateEmployee(request.getFirstname(), request.getLastanme());
    EmployeeEntity entity = this.mappingEmployee(request);
    entity.setEmployeeNumber(employeeNumber);
    this.employeeRepository.save(entity);
    return EmployeeSaveResponse.builder().employeeNumber(entity.getEmployeeNumber()).build();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  @Modifying
  @Transactional
  public void replaceEmployee(EmployeeRequest request, long id) {
    log.debug(LogConstant.REQUEST, request.toString());
    EmployeeEntity entity =
        this.employeeRepository.findById(id).<NoDataFoundException>orElseThrow(() -> {
          throw new NoDataFoundException(LogConstant.NO_DATA);
        });
    log.debug(LogConstant.DATABASE_DATA, entity.toString());
    request.setIdEmployee(id);
    this.employeeRepository.save(this.mappingEmployee(request));
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  @Transactional(readOnly = true)
  public List<Employee> searchBy(String search) {
    log.debug(LogConstant.EMPLOYEE_SEARCH, search);
    List<EmployeeEntity> entity = this.employeeRepository
        .findByEmployeeNumberOrFirstnameOrLastanmeIgnoreCase(search, search, search);
    if (ObjectUtils.isEmpty(entity)) {
      throw new NoDataFoundException(LogConstant.NO_DATA);
    }

    return entity.stream().map(this::mappingEmployee).collect(Collectors.toList());
  }

  /**
   * Valida si el usuario existe y sino existe genera el numero de empleado.
   * 
   * @param firstname nombre del empleado
   * @param lastname apellido del empleado
   * @return numero de empleado
   */
  private String validateEmployee(final String firstname, final String lastname) {
    String employeeNumber = "";
    EmployeeEntity entity = this.employeeRepository.findByFirstnameAndLastanme(firstname, lastname);
    if (!ObjectUtils.isEmpty(entity)) {
      FormattingTuple messages =
          MessageFormatter.format(LogConstant.EMPLOYEE_EXIST, firstname, lastname);
      log.error(messages.getMessage());
      throw new EmployeeExist(messages.getMessage());
    } else {
      employeeNumber = Utilities.generateEmployeeNumber(firstname, lastname);
    }
    return employeeNumber;
  }

  /**
   * Mapea una clase de tipo entity a una clase de DTO.
   * 
   * @param entity clase obtenida de base de datos
   * @return un objeto de tipo {@link Employee}
   */
  private Employee mappingEmployee(final EmployeeEntity entity) {
    ModelMapper mapper = Utilities.mapperForEmployee();
    return mapper.map(entity, Employee.class);
  }

  /**
   * Mapea una clase de tipo entity a una clase de DTO.
   * 
   * @param request objeto recibido en la peticion
   * @return un objeto de tipo {@link EmployeeEntity}
   */
  private EmployeeEntity mappingEmployee(final EmployeeRequest request) {
    ModelMapper mapper = Utilities.mapperForEmployeeEntity();
    return mapper.map(request, EmployeeEntity.class);
  }
}
