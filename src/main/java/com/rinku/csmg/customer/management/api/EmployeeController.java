package com.rinku.csmg.customer.management.api;

import com.rinku.csmg.customer.management.constant.ApiConstant;
import com.rinku.csmg.customer.management.constant.RoutesConstant;
import com.rinku.csmg.customer.management.exception.custom.ErrorResponse;
import com.rinku.csmg.customer.management.model.Employee;
import com.rinku.csmg.customer.management.model.EmployeeRequest;
import com.rinku.csmg.customer.management.model.EmployeeResponse;
import com.rinku.csmg.customer.management.model.EmployeeSaveResponse;
import com.rinku.csmg.customer.management.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * Apis para la administracion de empleados.
 * 
 * @author Felipe Monzón
 * @since 30 may. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@Api(tags = ApiConstant.API_EMPLOYEE_DATA)
@RequestMapping(path = RoutesConstant.BASE_PATH)
public class EmployeeController {
  /**
   * Servicio de empleados.
   */
  private final EmployeeService employeeService;

  /**
   * Consulta los empleados.
   * 
   * @param headers cabeceras necesarias para consumo
   * @return lista de tipo {@code Employee}
   */
  @ApiOperation(value = ApiConstant.API_OPERATION_EMPLOYEE_RETRIEVE)
  @ApiResponses(value = {
      @ApiResponse(code = ApiConstant.CODE_OK, message = ApiConstant.OK, response = Employee.class,
          responseContainer = ApiConstant.RESPONSE_CONTAINT_LIST),
      @ApiResponse(code = ApiConstant.CODE_BAD_REQUEST, message = ApiConstant.BAD_REQUEST,
          response = ErrorResponse.class),
      @ApiResponse(code = ApiConstant.CODE_INTERNAL_ERROR, message = ApiConstant.INTERNAL_ERROR,
          response = ErrorResponse.class)})
  @ApiImplicitParams({
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.DATE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_CHARSET),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_ENCODING),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_LANGUAGE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.AUTHORIZATION),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_TYPE),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_LENGTH),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.HOST),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.USER_AGENT),
      @ApiImplicitParam(name = ApiConstant.UUID, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER, value = ApiConstant.UUID_DESCRIPTION),
      @ApiImplicitParam(name = ApiConstant.CHANNEL_ID_HEADER, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER)})
  @GetMapping(path = RoutesConstant.EMPLOYEE_RETRIEVE_PATH,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Employee>> retrieve(@RequestHeader HttpHeaders headers) {
    return ResponseEntity.ok(this.employeeService.getEmployees());
  }

  /**
   * API para listar todas los empleados.
   *
   * @param pageable objeto de paginación {@code Pageable}
   * @return {@code Page} paginación de los empleados
   */
  @ApiOperation(value = ApiConstant.API_OPERATION_EMPLOYEE_RETRIEVE)
  @ApiResponses(value = {
      @ApiResponse(code = ApiConstant.CODE_OK, message = ApiConstant.OK,
          response = EmployeeResponse.class),
      @ApiResponse(code = ApiConstant.CODE_BAD_REQUEST, message = ApiConstant.BAD_REQUEST,
          response = ErrorResponse.class),
      @ApiResponse(code = ApiConstant.CODE_INTERNAL_ERROR, message = ApiConstant.INTERNAL_ERROR,
          response = ErrorResponse.class)})
  @ApiImplicitParams({
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.DATE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_CHARSET),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_ENCODING),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_LANGUAGE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.AUTHORIZATION),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_TYPE),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_LENGTH),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.HOST),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.USER_AGENT),
      @ApiImplicitParam(name = ApiConstant.UUID, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER, value = ApiConstant.UUID_DESCRIPTION),
      @ApiImplicitParam(name = ApiConstant.CHANNEL_ID_HEADER, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER)})
  @GetMapping(path = RoutesConstant.EMPLOYEE_PAGINATION_PATH,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EmployeeResponse> retrieve(@PageableDefault() Pageable pageable) {
    return ResponseEntity.ok(this.employeeService.getEmployees(pageable));
  }

  /**
   * Guarda un empleado.
   * 
   * @param headers cabeceras necesarias para la api.
   * @param request {@code Employee}
   * @return 200 si se guardo con exito
   */
  @ApiOperation(value = ApiConstant.API_OPERATION_EMPLOYEE_SAVE)
  @ApiResponses(value = {
      @ApiResponse(code = ApiConstant.CODE_OK, message = ApiConstant.OK,
          response = EmployeeSaveResponse.class),
      @ApiResponse(code = ApiConstant.CODE_BAD_REQUEST, message = ApiConstant.BAD_REQUEST,
          response = ErrorResponse.class),
      @ApiResponse(code = ApiConstant.CODE_INTERNAL_ERROR, message = ApiConstant.INTERNAL_ERROR,
          response = ErrorResponse.class)})
  @ApiImplicitParams({
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.DATE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_CHARSET),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_ENCODING),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_LANGUAGE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.AUTHORIZATION),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_TYPE),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_LENGTH),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.HOST),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.USER_AGENT),
      @ApiImplicitParam(name = ApiConstant.UUID, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER, value = ApiConstant.UUID_DESCRIPTION),
      @ApiImplicitParam(name = ApiConstant.CHANNEL_ID_HEADER, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER)})
  @PostMapping(path = RoutesConstant.EMPLOYEE_SAVE_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EmployeeSaveResponse> saveEmployee(@RequestHeader HttpHeaders headers,
      @RequestBody @Valid EmployeeRequest request) {
    return ResponseEntity.ok(this.employeeService.save(request));
  }

  /**
   * API para actualizar un empleado.
   *
   * @param request objeto {@code Employee} de la API de categorías
   * @param id identificador de la categoría
   */
  @ApiOperation(value = ApiConstant.API_OPERATION_EMPLOYEE_UPDATE)
  @ApiResponses(
      value = {@ApiResponse(code = ApiConstant.CODE_NO_CONTENT, message = ApiConstant.NO_CONTENT),
          @ApiResponse(code = ApiConstant.CODE_BAD_REQUEST, message = ApiConstant.BAD_REQUEST,
              response = ErrorResponse.class),
          @ApiResponse(code = ApiConstant.CODE_INTERNAL_ERROR, message = ApiConstant.INTERNAL_ERROR,
              response = ErrorResponse.class)})
  @ApiImplicitParams({
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.DATE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_CHARSET),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_ENCODING),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_LANGUAGE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.AUTHORIZATION),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_TYPE),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_LENGTH),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.HOST),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.USER_AGENT),
      @ApiImplicitParam(name = ApiConstant.UUID, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER, value = ApiConstant.UUID_DESCRIPTION),
      @ApiImplicitParam(name = ApiConstant.CHANNEL_ID_HEADER, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER)})
  @PutMapping(path = RoutesConstant.EMPLOYEE_UPDATE_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateEmployee(@RequestHeader HttpHeaders headers,
      @RequestBody @Valid EmployeeRequest request, @PathVariable long id) {
    this.employeeService.replaceEmployee(request, id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Busca un empleado por una opcion de busqueda.
   * 
   * @param headers cabeceras necesarias de consumo
   * @param search valor de busqueda
   * @return una empleado {@code Employee}
   */
  @ApiOperation(value = ApiConstant.API_TAG_EMPLOYEE_RETRIEVE,
      notes = ApiConstant.API_OPERATION_EMPLOYEE_SEARCH)
  @ApiResponses(value = {
      @ApiResponse(code = ApiConstant.CODE_OK, message = ApiConstant.OK, response = Employee.class),
      @ApiResponse(code = ApiConstant.CODE_BAD_REQUEST, message = ApiConstant.BAD_REQUEST,
          response = ErrorResponse.class),
      @ApiResponse(code = ApiConstant.CODE_INTERNAL_ERROR, message = ApiConstant.INTERNAL_ERROR,
          response = ErrorResponse.class)})
  @ApiImplicitParams({
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.DATE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_CHARSET),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_ENCODING),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.ACCEPT_LANGUAGE),
      @ApiImplicitParam(required = true, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.AUTHORIZATION),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_TYPE),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.CONTENT_LENGTH),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.HOST),
      @ApiImplicitParam(required = false, paramType = ApiConstant.PARAM_TYPE_HEADER,
          name = HttpHeaders.USER_AGENT),
      @ApiImplicitParam(name = ApiConstant.UUID, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER, value = ApiConstant.UUID_DESCRIPTION),
      @ApiImplicitParam(name = ApiConstant.CHANNEL_ID_HEADER, required = true,
          paramType = ApiConstant.PARAM_TYPE_HEADER)})
  @GetMapping(path = RoutesConstant.EMPLOYEE_SEARCH_PATH,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Employee>> searchBy(@RequestHeader HttpHeaders headers,
      @NotEmpty @PathVariable(name = ApiConstant.PARAM_SEARCH) String value) {
    return ResponseEntity.ok(this.employeeService.searchBy(value));
  }
}
