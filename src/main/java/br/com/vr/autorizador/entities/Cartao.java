/**
 * 
 */
package br.com.vr.autorizador.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Marcos.Diniz
 *
 */

@Getter
@Setter
@Entity
@Table(name = "tb_cartao")
public class Cartao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String numeroCartao;
	private String senha;
	private BigDecimal saldo;
	
	public Cartao() {
	}
	
	/**
	 * @param numeroCartao
	 * @param senha
	 * @param saldo
	 * @param transacoes
	 */
	public Cartao(String numeroCartao, String senha, BigDecimal saldo) {
		this.numeroCartao = numeroCartao;
		this.senha = senha;
		this.saldo = saldo;
	}

	public Cartao(String numeroCartao, String senhaCartao) {
		this.numeroCartao = numeroCartao;
		this.senha = senhaCartao;
	}
}
