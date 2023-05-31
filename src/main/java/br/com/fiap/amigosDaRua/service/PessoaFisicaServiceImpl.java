package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.PessoaFisica;
import br.com.fiap.amigosDaRua.entities.factories.PessoaFisicaMapperFactory;
import br.com.fiap.amigosDaRua.models.GetPessoaFisicaModel;
import br.com.fiap.amigosDaRua.models.InsertPessoaFisica;
import br.com.fiap.amigosDaRua.repositories.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaServiceImpl implements PessoaFisicaService{
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaFisicaMapperFactory pessoaFisicaMapperFactory;

    @Override
    public void createPessoaFisica(InsertPessoaFisica insertPessoaFisica){
        var pessoaFisica = pessoaFisicaMapperFactory.createEntityFromModel(insertPessoaFisica);
        pessoaFisicaRepository.save(pessoaFisica);
    }

    @Override
    public GetPessoaFisicaModel getPessoaFisicaById(Long id) {
        var pessoaFisica = pessoaFisicaRepository.findById(id).orElse(null);
        if(pessoaFisica == null){
            return null;
        }
        return pessoaFisicaMapperFactory.createModelFromEntity(pessoaFisica);
    }
}
