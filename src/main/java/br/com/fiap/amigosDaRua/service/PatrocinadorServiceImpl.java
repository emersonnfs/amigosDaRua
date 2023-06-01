package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Patrocinador;
import br.com.fiap.amigosDaRua.entities.TipoPatrocinadorEnum;
import br.com.fiap.amigosDaRua.entities.factories.PatrocinadorMapperFactory;
import br.com.fiap.amigosDaRua.models.GetPatrocinadorModel;
import br.com.fiap.amigosDaRua.models.InsertPatrocinadorModel;
import br.com.fiap.amigosDaRua.repositories.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatrocinadorServiceImpl implements PatrocinadorService{
    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private PatrocinadorMapperFactory patrocinadorMapperFactory;

    @Override
    public void createPatrocinador(InsertPatrocinadorModel insertPatrocinadorModel){
        var patrocinador = patrocinadorMapperFactory.createEntityFromModel(insertPatrocinadorModel);
        patrocinadorRepository.save(patrocinador);
    }

    @Override
    public Page<GetPatrocinadorModel> getAllPatrocinadores(Pageable pageable){
        Page<Patrocinador> patrocinadoresPage = patrocinadorRepository.findAll(pageable);
        return patrocinadoresPage.map(this::convertToGetPatrocinadorModel);
    }

    @Override
    public Page<GetPatrocinadorModel> getPatrocinadoresByTipo(String tipo, Pageable pageable){
        TipoPatrocinadorEnum tipoEnum = TipoPatrocinadorEnum.valueOf(tipo.toUpperCase());
        Page<Patrocinador> patrocinadoresPage = patrocinadorRepository.getPatrocinadoresByTipo(tipoEnum, pageable);
        return patrocinadoresPage.map(this::convertToGetPatrocinadorModel);
    }

    @Override
    public GetPatrocinadorModel getPatrocinadorById(Long id){
        Patrocinador patrocinador = patrocinadorRepository.findById(id).orElseThrow();
        return convertToGetPatrocinadorModel(patrocinador);
    }

    private GetPatrocinadorModel convertToGetPatrocinadorModel(Patrocinador patrocinador){
        return patrocinadorMapperFactory.createModelFromEntity(patrocinador);
    }
}
