package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.PessoaJuridica;
import br.com.fiap.amigosDaRua.entities.factories.PessoaJuridicaMapperFactory;
import br.com.fiap.amigosDaRua.models.GetPessoaJuridicaModel;
import br.com.fiap.amigosDaRua.models.InsertPessoaJuridica;
import br.com.fiap.amigosDaRua.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService{
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private PessoaJuridicaMapperFactory pessoaJuridicaMapperFactory;

    public PessoaJuridica createPessoaJuridica(InsertPessoaJuridica insertPessoaJuridica){
        var pessoaJuridica = pessoaJuridicaMapperFactory.createEntityFromModel(insertPessoaJuridica);
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    @Override
    public GetPessoaJuridicaModel getPessoaJuridicaById(Long id) {
        var pessoaJuridica = pessoaJuridicaRepository.findById(id).orElse(null);
        if(pessoaJuridica == null){
            return null;
        }
        return pessoaJuridicaMapperFactory.createModelFromEntity(pessoaJuridica);
    }
}
