package br.com.vr.autorizador.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.vr.autorizador.dto.CartaoDTO;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.exception.CartaoExistenteException;
import br.com.vr.autorizador.exception.CartaoInexistenteException;
import br.com.vr.autorizador.exception.CartaoInexistenteSaldoException;
import br.com.vr.autorizador.exception.CartaoSenhaInvalidaException;
import br.com.vr.autorizador.repositories.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	/**
	 * Metodo responsavel por criar o cartão
	 * @param cartaoDto
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public CartaoDTO create(CartaoDTO cartaoDto) {
		// realiza validacao se cartão já existe
		cartaoRepository.findById(cartaoDto.getNumeroCartao()).ifPresent(s -> {
			throw new CartaoExistenteException(cartaoDto);
		});
		// salva o cartao com saldo inicial de 500
		Cartao cartaoCreated = manterCartao(cartaoDto.fromDTO());
		
		return (CartaoDTO) new CartaoDTO().toDTO(cartaoCreated);
	}

	/**
	 * Metodo responsavel por recuperar o saldo do cartao disponivel
	 * @param numeroCartao
	 * @return saldo
	 */
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BigDecimal getSaldo(String numeroCartao) {
		// realiza consulta do saldo do cartão, caso não exista é lançada uma excessao
		return findByNumeroCartao(numeroCartao).orElseThrow(() -> new CartaoInexistenteSaldoException()).getSaldo();
	}
	

	/**
	 * Metodo responsavel por realizar as validações no cartão (senha e se existe o cartao)
	 * @param numeroCartao
	 * @param senhaCartao
	 * @return
	 */
	@Transactional(readOnly = true)
	public Cartao validarCartao(String numeroCartao, String senhaCartao) {
		//realiza consutla do cartão
		Optional<Cartao> cartaoOp= findByNumeroCartao(numeroCartao);
		//valida se o cartão ja existe
		cartaoOp.orElseThrow(() -> new CartaoInexistenteException("CARTAO_INEXISTENTE"));
		//valida se senha é igual
		return cartaoOp.filter(s -> s.getSenha().equals(senhaCartao)).orElseThrow(() -> new CartaoSenhaInvalidaException("SENHA_INVALIDA"));
	}

	/**
	 * Metodo responsavel por manter o cartao 
	 * @param cartao
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public Cartao manterCartao(Cartao cartao) {
		return cartaoRepository.save(cartao);
	}
	
	/**
	 * Metodo responsavel por recuperar o cartao pelo numero do cartao
	 * @param numeroCartao
	 * @return
	 */
	private Optional<Cartao> findByNumeroCartao(String numeroCartao) {
		return cartaoRepository.findById(numeroCartao);
	}

}
