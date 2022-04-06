package br.com.vr.autorizador.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.exception.CartaoException;

@Service
public class CartaoService {
	
	/**
	 * @param cartaoDto
	 * @throws CartaoException
	 */
	public void create(CartaoDTO cartaoDto) throws CartaoException{ 
		//realiza consutla se cartão já existe
		
		//salva o cartao com saldo inicial de 500
	}

	/**
	 * @param numeroCartao
	 * @return
	 * @throws CartaoException
	 */
	public BigDecimal getSaldo(String numeroCartao) throws CartaoException {
		
		//realiza consutla se cartão já existe
		
		//recupera o saldo disponivel
		
		return null;
	}

	/**
	 * @param transacaoDto
	 * @throws CartaoException
	 */
	public void realizarTransacao(TransacaoDTO transacaoDto) throws CartaoException {
		//realiza consutla se cartão já existe
		
		//realiza consutla se senha é valida
		
		//recupera e valida o saldo disponivel
		
		//realiza transaçao
	}

}
