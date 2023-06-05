package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Evento;
import br.com.fiap.amigosDaRua.models.GetEventoModel;
import br.com.fiap.amigosDaRua.models.InsertEventoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface EventoService {
    Evento createEvento(InsertEventoModel insertEventoModel);
    Page<GetEventoModel> getAllEventos(Pageable pageable);
    Page<GetEventoModel> getAllByHoraFimAfterCurrentDateTime(LocalDateTime currentDateTime, Pageable pageable);
    Page<GetEventoModel> getAllByHoraFimBeforeCurrentDateTime(LocalDateTime currentDateTime, Pageable pageable);
    GetEventoModel getEventoById(Long id);
    Evento updateEvento(Long id, InsertEventoModel insertEventoModel);
    Page<GetEventoModel> getAllByIdUsuario(Long idUsuario, Pageable pageable);
    void deleteEvento(Long id);

}
