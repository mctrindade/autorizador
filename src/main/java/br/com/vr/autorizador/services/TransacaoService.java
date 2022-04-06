package br.com.vr.autorizador.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.exception.CartaoException;
import br.com.vr.autorizador.repositories.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private CartaoService cartaoService;
	
	
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
