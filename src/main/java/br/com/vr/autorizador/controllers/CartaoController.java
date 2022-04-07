package br.com.vr.autorizador.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.services.CartaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;

	@ApiOperation(value = "Cria um cartão com saldo de 500")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Criação com sucesso"),
			@ApiResponse(code = 422, message = "Caso o cartão já exista") })
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<CartaoDTO> create(@RequestBody @Validated CartaoDTO cartaoDto) {
		CartaoDTO cartaoNovoDto = cartaoService.create(cartaoDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(cartaoNovoDto);
	}
	
	
	@ApiOperation(value = "Recupera o saldo disponivel do cartão")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Obtenção com sucesso"),
			@ApiResponse(code = 404, message = "Caso o cartão já exista") })
	@GetMapping(path = "{numeroCartao}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BigDecimal> getSaldo(@PathVariable String numeroCartao) {
		BigDecimal saldo = cartaoService.getSaldo(numeroCartao);
		return ResponseEntity.ok(saldo);
	}
}
