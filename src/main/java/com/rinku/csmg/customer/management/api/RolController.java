package com.rinku.csmg.customer.management.api;

import com.rinku.csmg.customer.management.constant.ApiConstant;
import com.rinku.csmg.customer.management.constant.RoutesConstant;
import com.rinku.csmg.customer.management.exception.custom.ErrorResponse;
import com.rinku.csmg.customer.management.model.Rol;
import com.rinku.csmg.customer.management.service.RolService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Apis para la administracion de los roles de los empleados.
 * 
 * @author Felipe Monzón
 * @date 15 jun. 2021
 * @enterprise Rinku Cinema
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@Api(tags = ApiConstant.API_ROLES_DATA)
@RequestMapping(path = RoutesConstant.BASE_PATH)
public class RolController {
  /**
   * Sericio de roles de empleados.
   */
  private final RolService rolService;

  /**
   * Consulta los roles de los empleados.
   * 
   * @param headers cabeceras necesarias para consumo
   * @return lista de tipo {@code Rol}
   */
  @ApiOperation(value = ApiConstant.API_TAG_ROLES_RETRIEVE,
      notes = ApiConstant.API_OPERATION_ROLES_RETRIEVE)
  @ApiResponses(value = {
      @ApiResponse(code = ApiConstant.CODE_OK, message = ApiConstant.OK, response = Rol.class,
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
  @GetMapping(path = RoutesConstant.ROLES_RETRIEVE_PATH,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Rol>> retrieve(@RequestHeader HttpHeaders headers) {
    return ResponseEntity.ok(this.rolService.getRoles());
  }
}
