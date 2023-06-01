package br.com.fiap.amigosDaRua.controllers;

import br.com.fiap.amigosDaRua.models.GetLocalModel;
import br.com.fiap.amigosDaRua.models.InsertLocalModel;
import br.com.fiap.amigosDaRua.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/local")
public class LocalController {
    @Autowired
    private LocalService localService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid InsertLocalModel local){
        localService.createLocal(local);
        return ResponseEntity.status(HttpStatus.CREATED).body(local);
    }

    @GetMapping
    public CollectionModel<EntityModel<GetLocalModel>> getAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String latitude,
            @RequestParam(required = false) String longitude,
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 5)
            Pageable pageable) {

        Page<GetLocalModel> locaisPage;
        if (nome != null) {
            locaisPage = localService.getLocaisByNome(nome, pageable);
        } else if (latitude != null && longitude != null) {
            locaisPage = localService.getLocaisByLatitudeLongitude(latitude, longitude, pageable);
        } else {
            locaisPage = localService.getAllLocais(pageable);
        }

        List<EntityModel<GetLocalModel>> localModels = locaisPage.stream()
                .map(local -> EntityModel.of(local,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalController.class).getAll(nome,
                                latitude, longitude, pageable)).withRel("locais"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                                .getLocalById(local.getId())).withSelfRel()))
                .collect(Collectors.toList());

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalController.class).getAll(nome, latitude, longitude, pageable)).withSelfRel();
        return CollectionModel.of(localModels, link);
    }

    @GetMapping("/{id}")
    public EntityModel<GetLocalModel> getLocalById(@PathVariable Long id){
        var local = localService.getLocalById(id);
        if(local == null){
            return EntityModel.of(null);
        }
        return EntityModel.of(local,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalController.class)
                        .getLocalById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LocalController.class).
                        getAll(null, null, null, null)).withRel("locais"));
    }


}
