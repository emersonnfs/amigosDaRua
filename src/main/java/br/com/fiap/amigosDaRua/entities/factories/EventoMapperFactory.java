package br.com.fiap.amigosDaRua.entities.factories;

import br.com.fiap.amigosDaRua.entities.Evento;
import br.com.fiap.amigosDaRua.entities.Parceria;
import br.com.fiap.amigosDaRua.entities.Patrocinador;
import br.com.fiap.amigosDaRua.models.GetEventoModel;
import br.com.fiap.amigosDaRua.models.GetPatrocinadorModel;
import br.com.fiap.amigosDaRua.models.InsertEventoModel;
import br.com.fiap.amigosDaRua.repositories.*;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventoMapperFactory {
    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;
    private LocalRepository localRepository;

    @Autowired
    public EventoMapperFactory(UsuarioRepository usuarioRepository, EventoRepository eventoRepository, LocalRepository localRepository) {
        this.modelMapper = new ModelMapper();
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
        this.localRepository = localRepository;

        configureModelMapper();
    }
    @Autowired
    private ParceriaRepository parceriaRepository;

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    private void configureModelMapper() {
        modelMapper.addMappings(new PropertyMap<InsertEventoModel, Evento>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    public Evento createEntityFromModel(InsertEventoModel model) {
        var entity = modelMapper.map(model, Evento.class);
        Long idUsuario = Long.parseLong(model.getIdUsuario());
        entity.getUsuario().setId(idUsuario);
        Long idLocal = Long.parseLong(model.getIdLocal());
        entity.getLocal().setId(idLocal);
        return entity;
    }

    public GetEventoModel createModelFromEntity(Evento entity) {
        var entityModel = modelMapper.map(entity, GetEventoModel.class);

        List<Parceria> listaParcerias = parceriaRepository.findByEventoId(entityModel.getId());
        List<GetPatrocinadorModel> listaPatrocinadores = new ArrayList<GetPatrocinadorModel>();
        for (Parceria parceria:listaParcerias){
            patrocinadorRepository.findById(parceria.getPatrocinador().getId());
            listaPatrocinadores.add(modelMapper.map(parceria.getPatrocinador(), GetPatrocinadorModel.class));
        }
        entityModel.setPatrocinadores(listaPatrocinadores);
        return entityModel;
    }

    public Evento updateEntityFromModel(InsertEventoModel model, Evento entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(model, entity);
        return entity;

    }
}