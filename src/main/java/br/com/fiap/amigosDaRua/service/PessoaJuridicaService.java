package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.PessoaJuridica;
import br.com.fiap.amigosDaRua.models.GetPessoaJuridicaModel;
import br.com.fiap.amigosDaRua.models.InsertPessoaJuridica;
import org.springframework.stereotype.Service;

@Service
public interface PessoaJuridicaService {
    PessoaJuridica createPessoaJuridica(InsertPessoaJuridica insertPessoaJuridica);

    GetPessoaJuridicaModel getPessoaJuridicaById(Long id);
}
