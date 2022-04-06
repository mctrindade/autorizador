package br.com.vr.autorizador.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.exception.CartaoException;
import br.com.vr.autorizador.exception.CartaoExistenteException;
import br.com.vr.autorizador.repositories.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	/**
	 * Metodo responsavel por criar o cartão
	 * @param cartaoDto
	 * @throws CartaoException
	 */
	public void create(CartaoDTO cartaoDto) throws CartaoException {
		// realiza validacao se cartão já existe
		cartaoRepository.findById(cartaoDto.getNumeroCartao()).ifPresent(s -> {
			throw new CartaoExistenteException(422, cartaoDto);
		});
		// salva o cartao com saldo inicial de 500
		cartaoRepository.save(cartaoDto.fromDTO());
	}

	/**
	 * Metodo responsavel por recuperar o saldo do cartao disponivel
	 * @param numeroCartao
	 * @return saldo
	 * @throws CartaoException
	 */
	public BigDecimal getSaldo(String numeroCartao) throws CartaoException {
		// realiza consulta do saldo do cartão, caso não exista é lançada uma excessao
		return cartaoRepository.findById(numeroCartao).orElseThrow(() -> new CartaoExistenteException(404)).getSaldo();
	}

}
