package br.com.vr.autorizador.dto;

import java.math.BigDecimal;

import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.entities.Transacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO implements BaseDTO<Transacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroCartao;
	private String senhaCartao;
	private BigDecimal valor;

	@Override
	public Object toDTO(Transacao transacao) {
		this.numeroCartao = transacao.getCartao().getNumeroCartao();
		this.senhaCartao = transacao.getCartao().getSenha();
		this.valor = transacao.getValorDebito();
		return this;
	}

	@Override
	public Transacao fromDTO() {
		Transacao transacao = new Transacao();
		transacao.setCartao(new Cartao(getNumeroCartao(), getSenhaCartao()));
		transacao.setValorDebito(getValor());
		return transacao;
	}

}
