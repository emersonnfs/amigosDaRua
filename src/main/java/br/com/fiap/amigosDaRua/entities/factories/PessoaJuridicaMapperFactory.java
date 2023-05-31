package br.com.fiap.amigosDaRua.entities.factories;

import br.com.fiap.amigosDaRua.entities.PessoaJuridica;
import br.com.fiap.amigosDaRua.models.GetPessoaJuridicaModel;
import br.com.fiap.amigosDaRua.models.InsertPessoaJuridica;
import br.com.fiap.amigosDaRua.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaMapperFactory {
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PessoaJuridicaMapperFactory(){
        this.modelMapper = new ModelMapper();
    }

    public PessoaJuridica createEntityFromModel(InsertPessoaJuridica model){
        var entity = modelMapper.map(model, PessoaJuridica.class);
        entity.setUsuario(usuarioRepository.getReferenceById(model.getIdUsuario()));
        return entity;
    }

    public GetPessoaJuridicaModel createModelFromEntity(PessoaJuridica entity){
        var entityModel = modelMapper.map(entity, GetPessoaJuridicaModel.class);
        entityModel.setUsuario(new UsuarioMapperFactory().createModelFromEntity(entity.getUsuario()));
        return entityModel;
    }
}
