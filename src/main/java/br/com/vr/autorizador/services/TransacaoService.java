package br.com.vr.autorizador.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vr.autorizador.dto.TransacaoDTO;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.entities.Transacao;
import br.com.vr.autorizador.exception.CartaoException;
import br.com.vr.autorizador.exception.CartaoSaldoException;
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
	@Transactional
	public void realizarTransacao(TransacaoDTO transacaoDto) throws CartaoException {

		Cartao cartao = cartaoService.validarCartao(transacaoDto.getNumeroCartao(), transacaoDto.getSenhaCartao());
		
		BigDecimal saldoDisponivel = validarSaldoDisponivel(transacaoDto, cartao);
		cartao.setSaldo(saldoDisponivel);
		
		cartaoService.manterCartao(cartao);
		
		Transacao transacao = transacaoDto.fromDTO();
		transacaoRepository.save(transacao);
	}


	private BigDecimal validarSaldoDisponivel(TransacaoDTO transacaoDto, Cartao cartao) {
		Optional<BigDecimal> saldoDisponivelOp = Optional.ofNullable(cartao.getSaldo().subtract(transacaoDto.getValor()));
		
		BigDecimal saldoDisponivel = saldoDisponivelOp.filter(saldo -> saldo.compareTo(BigDecimal.ZERO) >= 0).orElseThrow(() ->  new CartaoSaldoException("SALDO_INSUFICIENTE"));
		return saldoDisponivel;
	}
}
