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

import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.entities.Transacao;
import br.com.vr.autorizador.exception.CartaoException;
import br.com.vr.autorizador.exception.CartaoSaldoException;
import br.com.vr.autorizador.repositories.TransacaoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoServiceTest {

	@Mock
	private TransacaoRepository transacaoRepository;

	@Mock
	private CartaoService cartaoService;

	@InjectMocks
	private TransacaoService transacaoService;
	
	@Test
	void realizarTransacaoSuccessTest() throws Exception {
		Mockito.when(cartaoService.validarCartao(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(getCartao().get());
		Mockito.when(cartaoService.manterCartao(Mockito.any())).thenReturn(getCartao().get());
		Mockito.when(transacaoRepository.save(Mockito.any())).thenReturn(getTransacao());
		Transacao transacao = transacaoService.realizarTransacao(getTransacaoDTO());
		assertEquals(transacao.getValorDebito(), new BigDecimal(10));
	}
	
	@Test
	void realizarTransacaoCartaoSaldoExceptionTest() throws Exception {
		Mockito.when(cartaoService.validarCartao(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(getCartao().get());
		Mockito.when(cartaoService.manterCartao(Mockito.any())).thenReturn(getCartao().get());
		Mockito.when(transacaoRepository.save(Mockito.any())).thenReturn(getTransacao());
		try {
		TransacaoDTO transacaoDTO = getTransacaoDTO();
		transacaoDTO.setValor(new BigDecimal(501));
		transacaoService.realizarTransacao(transacaoDTO);
		} catch (CartaoException e) {
			assertThat(e).isInstanceOf(CartaoSaldoException.class);
		}
	}

	private TransacaoDTO getTransacaoDTO() {
		TransacaoDTO transacaoDTO = new TransacaoDTO();
		transacaoDTO.setNumeroCartao("6549873025634501");
		transacaoDTO.setSenhaCartao("12345");
		transacaoDTO.setValor(new BigDecimal(10));
		return transacaoDTO;
	}

	private Transacao getTransacao() {
		Transacao transacao = new Transacao();
		transacao.setCartao(getCartao().get());
		transacao.setValorDebito(new BigDecimal(10));
		return transacao;
	}

	private Optional<Cartao> getCartao() {
		Cartao cartao = new Cartao();
		cartao.setNumeroCartao("6549873025634501");
		cartao.setSenha("12345");
		cartao.setSaldo(new BigDecimal(500));
		return Optional.of(cartao);
	}

}
