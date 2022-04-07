package br.com.vr.autorizador.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService;
	
	@PostMapping
	public ResponseEntity<CartaoDTO> create(@RequestBody @Validated CartaoDTO cartaoDto) {
		CartaoDTO cartaoNovoDto = cartaoService.create(cartaoDto);
		return ResponseEntity.ok(cartaoNovoDto);
	}
	
	@GetMapping("{numeroCartao}")
	public ResponseEntity<BigDecimal> getSaldo(@PathVariable String numeroCartao){
		BigDecimal saldo = cartaoService.getSaldo(numeroCartao);
		return ResponseEntity.ok(saldo);
	}
}
