package br.com.vr.autorizador.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.dto.TransacaoDTO;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	@PostMapping
	public ResponseEntity<CartaoDTO> create(@RequestBody @Validated CartaoDTO cartaoDto) {
		//try {
			return ResponseEntity.ok(new CartaoDTO());
		//} catch (CartaoExistenteException e) {
			//throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		//}
	}
	
	@GetMapping("{numeroCartao}")
	public ResponseEntity<BigDecimal> getSaldo(@PathVariable String numeroCartao){
		//try {
			BigDecimal saldo = new BigDecimal(10);
			return ResponseEntity.ok(saldo);
		//} catch (CartaoSaldoException e) {
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		//}
	}
	
	@PostMapping("transacoes")
	public ResponseEntity<String> transaction (@RequestBody TransacaoDTO transacaoDto){
		return ResponseEntity.ok("Ok");
	}
}
