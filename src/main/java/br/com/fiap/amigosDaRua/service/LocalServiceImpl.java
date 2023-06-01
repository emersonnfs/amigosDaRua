package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Local;
import br.com.fiap.amigosDaRua.entities.factories.LocalMapperFactory;
import br.com.fiap.amigosDaRua.models.GetLocalModel;
import br.com.fiap.amigosDaRua.models.InsertLocalModel;
import br.com.fiap.amigosDaRua.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LocalServiceImpl implements LocalService{
    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalMapperFactory localMapperFactory;

    @Override
    public Local createLocal(InsertLocalModel insertLocalModel){
        var local = localMapperFactory.createEntityFromModel(insertLocalModel);
        return localRepository.save(local);
    }

    @Override
    public Page<GetLocalModel> getAllLocais(Pageable pageable){
        Page<Local> locaisPage = localRepository.findAll(pageable);
        return locaisPage.map(this::convertToGetLocalModel);
    }

    @Override
    public Page<GetLocalModel> getLocaisByNome(String nome, Pageable pageable){
        Page<Local> locaisPage = localRepository.findByNomeIgnoreCaseContaining(nome, pageable);
        return locaisPage.map(this::convertToGetLocalModel);
    }

    @Override
    public GetLocalModel getLocalById(Long id){
        var local = localRepository.findById(id).orElse(null);
        if(local == null){
            return null;
        }
        return localMapperFactory.createModelFromEntity(local);
    }

    @Override
    public Page<GetLocalModel> getLocaisByLatitudeLongitude(String latitude, String longitude, Pageable pageable){
        BigDecimal lat = new BigDecimal(latitude);
        BigDecimal lon = new BigDecimal(longitude);
        Page<Local> locaisPage = localRepository.getLocalByLatitudeAndLongitude(lat, lon, pageable);
        return locaisPage.map(this::convertToGetLocalModel);
    }


    private GetLocalModel convertToGetLocalModel(Local local){
        return localMapperFactory.createModelFromEntity(local);
    }
}
