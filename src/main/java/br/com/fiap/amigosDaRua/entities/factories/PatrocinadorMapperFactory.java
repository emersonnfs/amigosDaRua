package br.com.fiap.amigosDaRua.entities.factories;

import br.com.fiap.amigosDaRua.entities.Patrocinador;
import br.com.fiap.amigosDaRua.models.GetPatrocinadorModel;
import br.com.fiap.amigosDaRua.models.InsertPatrocinadorModel;
import br.com.fiap.amigosDaRua.repositories.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatrocinadorMapperFactory {
    private ModelMapper modelMapper;

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    public PatrocinadorMapperFactory(){
        this.modelMapper = new ModelMapper();
    }

    public Patrocinador createEntityFromModel(InsertPatrocinadorModel model){
        var entity = modelMapper.map(model, Patrocinador.class);
        return entity;
    }

    public GetPatrocinadorModel createModelFromEntity(Patrocinador entity){
        var entityModel = modelMapper.map(entity, GetPatrocinadorModel.class);
        return entityModel;
    }
}
