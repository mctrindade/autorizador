package br.com.vr.autorizador.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.exception.CartaoException;
import br.com.vr.autorizador.exception.CartaoExistenteException;
import br.com.vr.autorizador.exception.CartaoInexistenteException;
import br.com.vr.autorizador.exception.CartaoInexistenteSaldoException;
import br.com.vr.autorizador.exception.CartaoSenhaInvalidaException;
import br.com.vr.autorizador.repositories.CartaoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CartaoServiceTest {

	@Mock
	private CartaoRepository cartaoRepository;

	@InjectMocks
	private CartaoService cartaoService;

	@Test
	void createCartaoSuccessTest() throws Exception {
		Optional<Cartao> cartaoOp = getCartao();
		Mockito.when(cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(cartaoRepository.save(Mockito.any())).thenReturn(cartaoOp.get());
		CartaoDTO cartaoDTO = cartaoService.create(getCartaoDTO());
		assertEquals(cartaoDTO.fromDTO().getNumeroCartao(), cartaoOp.get().getNumeroCartao());

	}

	@Test
	void createCartaoCartaoExistenteExceptionTest() throws Exception {
		Optional<Cartao> cartaoOp = getCartao();
		Mockito.when(cartaoRepository.findById(Mockito.anyString())).thenReturn(cartaoOp);
		try {
			cartaoService.create(getCartaoDTO());
		} catch (CartaoException e) {
			assertThat(e).isInstanceOf(CartaoExistenteException.class);
		}
	}

	@Test
	void getSaldoCartaoSuccessTest() throws Exception {
		Optional<Cartao> cartaoOp = getCartao();
		Mockito.when(cartaoRepository.findById(Mockito.anyString())).thenReturn(cartaoOp);
		BigDecimal saldoDisponivel = cartaoService.getSaldo(getCartaoDTO().getNumeroCartao());
		assertEquals(saldoDisponivel, new BigDecimal(500));
	}

	@Test
	void getSaldoCartaoInexistenteSaldoExceptionTest() throws Exception {
		Mockito.when(cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		try {
			cartaoService.getSaldo(getCartaoDTO().getNumeroCartao());
		} catch (CartaoException e) {
			assertThat(e).isInstanceOf(CartaoInexistenteSaldoException.class);
		}
	}

	@Test
	void validarCartaoSuccessTest() throws Exception {
		Optional<Cartao> cartaoOp = getCartao();
		Mockito.when(cartaoRepository.findById(Mockito.anyString())).thenReturn(cartaoOp);
		Cartao cartao = cartaoService.validarCartao(getCartaoDTO().getNumeroCartao(), getCartaoDTO().getSenha());
		assertThat(cartao).isEqualTo(cartaoOp.get());

	}

	@Test
	void validarCartaoInexistenteExceptionTest() throws Exception {
		Mockito.when(cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		try {
			cartaoService.validarCartao(getCartaoDTO().getNumeroCartao(), getCartaoDTO().getSenha());
		} catch (CartaoException e) {
			assertThat(e).isInstanceOf(CartaoInexistenteException.class);
		}
	}
	
	@Test
	void validarCartaoSenhaInvalidaExceptionTest() throws Exception {
		Optional<Cartao> cartaoOp = getCartao();
		Mockito.when(cartaoRepository.findById(Mockito.anyString())).thenReturn(cartaoOp);
		try {
			cartaoService.validarCartao(getCartaoDTO().getNumeroCartao(), "1234");
		} catch (CartaoException e) {
			assertThat(e).isInstanceOf(CartaoSenhaInvalidaException.class);
		}
	}

	private Optional<Cartao> getCartao() {
		Cartao cartao = new Cartao();
		cartao.setNumeroCartao("6549873025634501");
		cartao.setSenha("12345");
		cartao.setSaldo(new BigDecimal(500));
		return Optional.of(cartao);
	}

	private CartaoDTO getCartaoDTO() {
		CartaoDTO cartaoDTO = new CartaoDTO();
		cartaoDTO.setNumeroCartao("6549873025634501");
		cartaoDTO.setSenha("12345");
		return cartaoDTO;
	}
}
