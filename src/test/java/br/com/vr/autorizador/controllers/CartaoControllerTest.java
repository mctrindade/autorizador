package br.com.vr.autorizador.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.exception.CartaoExistenteException;
import br.com.vr.autorizador.exception.CartaoInexistenteSaldoException;
import br.com.vr.autorizador.services.CartaoService;

@SpringBootTest
@AutoConfigureMockMvc
public class CartaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartaoService cartaoService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void postCreateCartaoSuccessTest() throws Exception {
		CartaoDTO cartaoNovoDto = getCartaoDTO();
		Mockito.when(cartaoService.create(Mockito.any())).thenReturn(cartaoNovoDto);
		mockMvc.perform(post("/cartoes").contentType("application/json")
				.content(objectMapper.writeValueAsString(cartaoNovoDto))).andExpect(status().isCreated());
	}
	
	@Test
	void postCreateCartaoExistenteExceptionTest() throws Exception {
		CartaoDTO cartaoNovoDto = getCartaoDTO();
		Mockito.when(cartaoService.create(Mockito.any())).thenThrow(new CartaoExistenteException(cartaoNovoDto));
		mockMvc.perform(post("/cartoes").contentType("application/json")
				.content(objectMapper.writeValueAsString(cartaoNovoDto))).andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	void getSaldoCartaoSuccessTest() throws Exception {
		BigDecimal saldoDisponivel = new BigDecimal(500);
		Mockito.when(cartaoService.getSaldo(Mockito.anyString())).thenReturn(saldoDisponivel);
		mockMvc.perform(get("/cartoes/6549873025634501").contentType("application/json")
				.content(objectMapper.writeValueAsString(saldoDisponivel))).andExpect(status().isOk());
	}
	
	@Test
	void getSaldoCartaoInexistenteSaldoExceptionTest() throws Exception {
		BigDecimal saldoDisponivel = new BigDecimal(500);
		Mockito.when(cartaoService.getSaldo(Mockito.anyString())).thenThrow(new CartaoInexistenteSaldoException());
		mockMvc.perform(get("/cartoes/6549873025634501").contentType("application/json")
				.content(objectMapper.writeValueAsString(saldoDisponivel))).andExpect(status().isNotFound());
	}

	private CartaoDTO getCartaoDTO() {
		CartaoDTO cartaoDTO = new CartaoDTO();
		cartaoDTO.setNumeroCartao("6549873025634501");
		cartaoDTO.setSenha("12345");
		return cartaoDTO;
	}
}
