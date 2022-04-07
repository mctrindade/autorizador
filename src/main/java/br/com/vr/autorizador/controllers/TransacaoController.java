package br.com.vr.autorizador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.services.TransacaoService;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
		
	@PostMapping
	public ResponseEntity<String> transaction (@RequestBody TransacaoDTO transacaoDto){
		transacaoService.realizarTransacao(transacaoDto);
		return ResponseEntity.ok("Ok");
	}
}
