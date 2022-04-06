package br.com.vr.autorizador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vr.autorizador.entities.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String>{

}
