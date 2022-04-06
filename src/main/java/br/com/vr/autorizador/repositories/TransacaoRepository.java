package br.com.vr.autorizador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vr.autorizador.entities.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
