package br.com.vr.autorizador.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	@Transactional
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
	public BigDecimal getSaldo(String numeroCartao) {
		// realiza consulta do saldo do cartão, caso não exista é lançada uma excessao
		return findByNumeroCartao(numeroCartao).orElseThrow(() -> new CartaoInexistenteSaldoException()).getSaldo();
	}
	
	/**
	 * Metodo responsavel por recuperar o cartao pelo numero do cartao
	 * @param numeroCartao
	 * @return
	 */
	public Optional<Cartao> findByNumeroCartao(String numeroCartao) {
		return cartaoRepository.findById(numeroCartao);
	}

	/**
	 * @param numeroCartao
	 * @param senhaCartao
	 * @return
	 */
	public Cartao validarCartao(String numeroCartao, String senhaCartao) {
		//realiza consutla se cartão já existe
		Optional<Cartao> cartaoOp= findByNumeroCartao(numeroCartao);
		cartaoOp.orElseThrow(() -> new CartaoInexistenteException("CARTAO_INEXISTENTE"));
		//valida se senha é igual
		return cartaoOp.filter(s -> s.getSenha().equals(senhaCartao)).orElseThrow(() -> new CartaoSenhaInvalidaException("SENHA_INVALIDA"));
	}

	/**
	 * @param cartao
	 * @return
	 */
	public Cartao manterCartao(Cartao cartao) {
		return cartaoRepository.save(cartao);
	}

}
