package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Evento;
import br.com.fiap.amigosDaRua.entities.Parceria;
import br.com.fiap.amigosDaRua.entities.factories.EventoMapperFactory;
import br.com.fiap.amigosDaRua.models.GetEventoModel;
import br.com.fiap.amigosDaRua.models.InsertEventoModel;
import br.com.fiap.amigosDaRua.repositories.EventoRepository;
import br.com.fiap.amigosDaRua.repositories.ParceriaRepository;
import br.com.fiap.amigosDaRua.repositories.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoServiceImpl implements EventoService{
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private EventoMapperFactory eventoMapperFactory;

    @Autowired
    private ParceriaRepository parceriaRepository;
    @Autowired
    private ParceriaServiceImpl parceriaService;

    @Override
    public Evento createEvento(InsertEventoModel insertEventoModel) {
        var evento = eventoMapperFactory.createEntityFromModel(insertEventoModel);
        var eventoSalvo = eventoRepository.save(evento);

        List<String> idPatrocinadores = insertEventoModel.getIdPatrocinador();
        if (idPatrocinadores != null) {
            for (String idPatrocinador : idPatrocinadores) {
                Parceria parceria = new Parceria();
                parceria.setEvento(eventoSalvo);
                Long idPatrocinadorLong = Long.parseLong(idPatrocinador);
                parceria.setPatrocinador(patrocinadorRepository.getReferenceById(idPatrocinadorLong));
                parceriaService.createParceria(parceria);
            }
        }

        return eventoSalvo;
    }

    @Override
    public Page<GetEventoModel> getAllByHoraFimAfterCurrentDateTime(LocalDateTime currentDateTime, Pageable pageable){
        var eventos = eventoRepository.findAllByHoraFimAfterCurrentDateTime(currentDateTime, pageable);
        return eventos.map(this::convertToGetEventoModel);
    }

    @Override
    public Page<GetEventoModel> getAllByHoraFimBeforeCurrentDateTime(LocalDateTime currentDateTime, Pageable pageable){
        var eventos = eventoRepository.findAllByHoraFimBeforeCurrentDateTime(currentDateTime, pageable);
        return eventos.map(this::convertToGetEventoModel);
    }

    @Override
    public Page<GetEventoModel> getAllEventos(Pageable pageable){
        var eventos = eventoRepository.findAll(pageable);
        return eventos.map(this::convertToGetEventoModel);
    }

    @Override
    public GetEventoModel getEventoById(Long id){
        var evento = eventoRepository.findById(id).orElseThrow();
        return convertToGetEventoModel(evento);
    }

    @Override
    public Page<GetEventoModel> getAllByIdUsuario(Long idUsuario, Pageable pageable){
        var eventos = eventoRepository.findAllByIdUsuario(idUsuario, pageable);
        return eventos.map(this::convertToGetEventoModel);
    }

    @Override
    public Evento updateEvento(Long id, InsertEventoModel insertEventoModel){
        var evento = eventoRepository.findById(id).orElseThrow();
        eventoMapperFactory.updateEntityFromModel(insertEventoModel, evento);
        return eventoRepository.save(evento);
    }

    @Override
    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }

    private GetEventoModel convertToGetEventoModel(Evento evento){
        return eventoMapperFactory.createModelFromEntity(evento);
    }
}
