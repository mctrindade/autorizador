/**
 * 
 */
package br.com.vr.autorizador.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Marcos.Diniz
 *
 */
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
	
	/*@OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
	private Set<Transacao> transacoes;*/
	
	public Cartao() {
	}
	
	/**
	 * @param numeroCartao
	 * @param senha
	 * @param saldo
	 * @param transacoes
	 */
	public Cartao(String numeroCartao, String senha, BigDecimal saldo) {
		super();
		this.numeroCartao = numeroCartao;
		this.senha = senha;
		this.saldo = saldo;
	}

	/**
	 * @return the numeroCartao
	 */
	public String getNumeroCartao() {
		return numeroCartao;
	}

	/**
	 * @param numeroCartao the numeroCartao to set
	 */
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the transacoes
	 */
	/*public Set<Transacao> getTransacoes() {
		return transacoes;
	}*/

	/**
	 * @param transacoes the transacoes to set
	 */
	/*public void setTransacoes(Set<Transacao> transacoes) {
		this.transacoes = transacoes;
	}*/
}
