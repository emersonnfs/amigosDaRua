package br.com.fiap.amigosDaRua.controllers;

import br.com.fiap.amigosDaRua.models.GetEventoModel;
import br.com.fiap.amigosDaRua.models.InsertEventoModel;
import br.com.fiap.amigosDaRua.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody InsertEventoModel evento){
        var eventoEntity = eventoService.createEvento(evento);
        var eventoModel = eventoService.getEventoById(eventoEntity.getId());
        return ResponseEntity.ok(eventoModel);
    }

    @GetMapping
    public CollectionModel<EntityModel<GetEventoModel>> getAllEventos(
            @PageableDefault(sort = "horaInicio", direction = Sort.Direction.ASC, page = 0, size = 3)
            Pageable pageable) {
        Page<GetEventoModel> eventos = eventoService.getAllEventos(pageable);
        List<EntityModel<GetEventoModel>> eventoModels = eventos.stream()
                .map(evento -> EntityModel.of(evento,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                                .getAllDisponivel(pageable)).withRel("eventosDisponiveis"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                                .getEventoById(evento.getId())).withSelfRel()))
                .collect(Collectors.toList());

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).getAllEventos(pageable)).withSelfRel();
        return CollectionModel.of(eventoModels, link);
    }

    @GetMapping("/disponivel")
    public CollectionModel<EntityModel<GetEventoModel>> getAllDisponivel(
            @PageableDefault(sort = "horaInicio", direction = Sort.Direction.ASC, page = 0, size = 3)
            Pageable pageable) {
        LocalDateTime dataAtual = LocalDateTime.now();
        Page<GetEventoModel> eventos = eventoService.getAllByHoraFimAfterCurrentDateTime(dataAtual, pageable);
        List<EntityModel<GetEventoModel>> eventoModels = eventos.stream()
                .map(evento -> EntityModel.of(evento,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                                .getAllEventos(pageable)).withRel("eventos"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                                .getEventoById(evento.getId())).withSelfRel()))
                .collect(Collectors.toList());

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).getAllDisponivel(pageable)).withSelfRel();
        return CollectionModel.of(eventoModels, link);
    }

    @GetMapping("/indisponivel")
    public CollectionModel<EntityModel<GetEventoModel>> getAllIndisponivel(
            @PageableDefault(sort = "horaInicio", direction = Sort.Direction.DESC, page = 0, size = 3)
            Pageable pageable) {
        LocalDateTime dataAtual = LocalDateTime.now();
        Page<GetEventoModel> eventos = eventoService.getAllByHoraFimBeforeCurrentDateTime(dataAtual, pageable);
        List<EntityModel<GetEventoModel>> eventoModels = eventos.stream()
                .map(evento -> EntityModel.of(evento,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                                .getAllDisponivel(pageable)).withRel("eventosDispoiniveis"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                                .getEventoById(evento.getId())).withSelfRel()))
                .collect(Collectors.toList());

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class).getAllIndisponivel(pageable)).withSelfRel();
        return CollectionModel.of(eventoModels, link);
    }

    @GetMapping("/{id}")
    public EntityModel<GetEventoModel> getEventoById(@PathVariable Long id) {
        var evento = eventoService.getEventoById(id);

        return EntityModel.of(evento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                        .getAllDisponivel(null)).withRel("eventosDisponiveis"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                        .getAllEventos(null)).withRel("eventos"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventoController.class)
                        .getEventoById(id)).withSelfRel());
    }

}
