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
import org.springframework.web.server.ResponseStatusException;

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.exception.CartaoException;
import br.com.vr.autorizador.exception.CartaoExistenteException;
import br.com.vr.autorizador.services.CartaoService;
import br.com.vr.autorizador.services.TransacaoService;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private TransacaoService transacaoService;
	
	@PostMapping
	public ResponseEntity<CartaoDTO> create(@RequestBody @Validated CartaoDTO cartaoDto) {
		try {
			cartaoService.create(cartaoDto);
			return ResponseEntity.ok(cartaoDto);
		} catch (CartaoExistenteException e) {
			throw new ResponseStatusException(e.getCodStatus(), e.getBody().toString(), e);
		}
	}
	
	@GetMapping("{numeroCartao}")
	public ResponseEntity<BigDecimal> getSaldo(@PathVariable String numeroCartao){
		try {
			BigDecimal saldo = cartaoService.getSaldo(numeroCartao);
			return ResponseEntity.ok(saldo);
		} catch (CartaoExistenteException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("transacoes")
	public ResponseEntity<String> transaction (@RequestBody TransacaoDTO transacaoDto){
		try {
			transacaoService.realizarTransacao(transacaoDto);
			return ResponseEntity.ok("Ok");
		} catch (CartaoException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
}
