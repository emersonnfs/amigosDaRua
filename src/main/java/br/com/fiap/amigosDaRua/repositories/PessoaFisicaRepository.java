package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica,Long> {
}
