package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica,Long> {
}
