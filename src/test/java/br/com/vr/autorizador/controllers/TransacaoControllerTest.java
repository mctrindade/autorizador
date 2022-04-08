package br.com.vr.autorizador.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.entities.Transacao;
import br.com.vr.autorizador.exception.CartaoInexistenteException;
import br.com.vr.autorizador.services.CartaoService;
import br.com.vr.autorizador.services.TransacaoService;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TransacaoService transacaoService;
	
	@MockBean
	private CartaoService cartaoService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void postTransactionSuccessTest() throws Exception {
		TransacaoDTO transacaoDTO = getTransacaoDTO();
		Mockito.when(transacaoService.realizarTransacao(Mockito.any())).thenReturn(new Transacao());
		mockMvc.perform(post("/transacoes").contentType("application/json")
				.content(objectMapper.writeValueAsString(transacaoDTO))).andExpect(status().isCreated());
	}
	
	@Test
	void postTransactionCartaoInexistenteExceptionTest() throws Exception {
		TransacaoDTO transacaoDTO = getTransacaoDTO();
		Mockito.doThrow(new CartaoInexistenteException("CARTAO_INEXISTENTE")).when(transacaoService).realizarTransacao(Mockito.any());
		mockMvc.perform(post("/transacoes").contentType("application/json")
				.content(objectMapper.writeValueAsString(transacaoDTO))).andExpect(status().isUnprocessableEntity());
	}
	
	private TransacaoDTO getTransacaoDTO() {
		TransacaoDTO transacaoDTO = new TransacaoDTO();
		transacaoDTO.setNumeroCartao("6549873025634501");
		transacaoDTO.setSenhaCartao("12345");
		transacaoDTO.setValor(new BigDecimal(10));
		return transacaoDTO;
	}
}
