package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.factories.LocalMapperFactory;
import br.com.fiap.amigosDaRua.models.InsertLocalModel;
import br.com.fiap.amigosDaRua.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalServiceImpl implements LocalService{
    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalMapperFactory localMapperFactory;

    public void createLocal(InsertLocalModel insertLocalModel){
        var local = localMapperFactory.createEntityFromModel(insertLocalModel);
        localRepository.save(local);
    }
}
