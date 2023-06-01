package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.PessoaFisica;
import br.com.fiap.amigosDaRua.entities.PessoaJuridica;
import br.com.fiap.amigosDaRua.models.GetPessoaFisicaModel;
import br.com.fiap.amigosDaRua.models.InsertPessoaFisica;
import org.springframework.stereotype.Service;

@Service
public interface PessoaFisicaService {
    PessoaFisica createPessoaFisica(InsertPessoaFisica insertPessoaFisica);

    GetPessoaFisicaModel getPessoaFisicaById(Long id);

}
