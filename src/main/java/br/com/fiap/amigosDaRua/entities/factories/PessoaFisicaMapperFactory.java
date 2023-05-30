package br.com.fiap.amigosDaRua.entities.factories;

import br.com.fiap.amigosDaRua.entities.PessoaFisica;
import br.com.fiap.amigosDaRua.models.GetPessoaFisicaModel;
import br.com.fiap.amigosDaRua.models.InsertPessoaFisica;
import br.com.fiap.amigosDaRua.repositories.UsuarioRepository;
import br.com.fiap.amigosDaRua.service.UsuarioService;
import br.com.fiap.amigosDaRua.service.UsuarioServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaMapperFactory {
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PessoaFisicaMapperFactory(){
        this.modelMapper = new ModelMapper();
    }

    public PessoaFisica createEntityFromModel(InsertPessoaFisica model){
        var entity = modelMapper.map(model, PessoaFisica.class);
        entity.setUsuario(usuarioRepository.getReferenceById(model.getIdUsuario()));
        return entity;
    }

    public GetPessoaFisicaModel createModelFromEntity(PessoaFisica entity){
        var entityModel = modelMapper.map(entity, GetPessoaFisicaModel.class);
        entityModel.setUsuario(new UsuarioMapperFactory().createModelFromEntity(entity.getUsuario()));
        return entityModel;
    }
}
