package br.com.fiap.amigosDaRua.entities.factories;

import br.com.fiap.amigosDaRua.entities.Local;
import br.com.fiap.amigosDaRua.models.InsertLocalModel;
import br.com.fiap.amigosDaRua.repositories.LocalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalMapperFactory {
    private ModelMapper modelMapper;

    @Autowired
    private LocalRepository localRepository;

    public LocalMapperFactory(){
        this.modelMapper = new ModelMapper();
    }

    public Local createEntityFromModel(InsertLocalModel model){
        var entity = modelMapper.map(model, Local.class);
        return entity;
    }
}
