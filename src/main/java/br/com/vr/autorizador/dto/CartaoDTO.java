package br.com.vr.autorizador.dto;

import java.math.BigDecimal;

import br.com.vr.autorizador.entities.Cartao;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoDTO implements BaseDTO<Cartao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String senha;
	private String numeroCartao;
	@Getter(value = AccessLevel.NONE)
	private BigDecimal saldo;
	
	@Override
	public Object toDTO(Cartao cartao) {
		this.numeroCartao = cartao.getNumeroCartao();
		this.senha = cartao.getSenha();
		this.saldo = cartao.getSaldo();
		return this;
	}
	@Override
	public Cartao fromDTO() {
		Cartao cartao = new Cartao();
		cartao.setNumeroCartao(getNumeroCartao());
		cartao.setSaldo(new BigDecimal(500));
		cartao.setSenha(getSenha());
		return cartao;
	}
	@Override
	public String toString() {
		return "senha:" + senha + ", numeroCartao:" + numeroCartao;
	}
	
}
