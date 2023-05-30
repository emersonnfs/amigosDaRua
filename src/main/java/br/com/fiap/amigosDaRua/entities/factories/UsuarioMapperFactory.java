package br.com.fiap.amigosDaRua.entities.factories;

import br.com.fiap.amigosDaRua.entities.Usuario;
import br.com.fiap.amigosDaRua.models.GetUsuarioModel;
import br.com.fiap.amigosDaRua.models.UpdateUsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperFactory {

    private ModelMapper modelMapper;

    public UsuarioMapperFactory() {
        this.modelMapper = new ModelMapper();
    }

    public Usuario createEntityFromModel(GetUsuarioModel model) {
        return modelMapper.map(model, Usuario.class);
    }

    public GetUsuarioModel createModelFromEntity(Usuario entity) {
        return modelMapper.map(entity, GetUsuarioModel.class);
    }

    public Usuario updateEntityFromModel(UpdateUsuarioModel model, Usuario entity) {
        modelMapper.map(model, entity);
        return entity;
    }
}

