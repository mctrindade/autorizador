package br.com.vr.autorizador.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.services.TransacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
	
	@ApiOperation(value = "Realiza uma transação")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Transação realizada com sucesso"),
			@ApiResponse(code = 422, message = "SALDO_INSUFICIENTE|SENHA_INVALIDA|CARTAO_INEXISTENTE") })
	@PostMapping(produces="application/json", consumes="application/json" )
	public ResponseEntity<String> transaction (@RequestBody TransacaoDTO transacaoDto){
		transacaoService.realizarTransacao(transacaoDto);
		return ResponseEntity.ok("Ok");
	}
}
