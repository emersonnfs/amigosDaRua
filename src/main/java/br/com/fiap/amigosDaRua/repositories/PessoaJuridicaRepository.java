package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica,Long> {
}
