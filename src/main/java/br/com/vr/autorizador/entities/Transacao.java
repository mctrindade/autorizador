package br.com.vr.autorizador.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_transacao")
public class Transacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "numeroCartao")
	private Cartao cartao;
	private BigDecimal valorDebito;
	
	public Transacao() {
	}

	/**
	 * @param id
	 * @param cartao
	 * @param valorDebito
	 */
	public Transacao(Long id, Cartao cartao, BigDecimal valorDebito) {
		super();
		this.id = id;
		this.cartao = cartao;
		this.valorDebito = valorDebito;
	}
}
