package br.com.fiap.amigosDaRua.controllers;

import br.com.fiap.amigosDaRua.models.GetPatrocinadorModel;
import br.com.fiap.amigosDaRua.models.InsertPatrocinadorModel;
import br.com.fiap.amigosDaRua.service.PatrocinadorService;
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
@RequestMapping("/api/patrocinador")
public class PatrocinadorController {
    @Autowired
    private PatrocinadorService patrocinadorService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid InsertPatrocinadorModel model){
        var patrocinadorEntity = patrocinadorService.createPatrocinador(model);
        var patrocinadorModel = patrocinadorService.getPatrocinadorById(patrocinadorEntity.getId());
        return ResponseEntity.ok(patrocinadorModel);
    }

    @GetMapping
    public CollectionModel<EntityModel<GetPatrocinadorModel>> getAll(
            @RequestParam(required = false) String tipo,
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 5)
            Pageable pageable) {
        Page<GetPatrocinadorModel> patrocinadoresPage;
        if (tipo != null) {
            patrocinadoresPage = patrocinadorService.getPatrocinadoresByTipo(tipo, pageable);
        } else {
            patrocinadoresPage = patrocinadorService.getAllPatrocinadores(pageable);
        }

        List<EntityModel<GetPatrocinadorModel>> patrocinadorModels = patrocinadoresPage.stream()
                .map(patrocinador -> EntityModel.of(patrocinador,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrocinadorController.class).getAll(tipo
                                , pageable)).withRel("patrocinadores"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrocinadorController.class)
                                .getPatrocinadorById(patrocinador.getId())).withSelfRel()))
                .collect(Collectors.toList());

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrocinadorController.class).getAll(tipo, pageable)).withSelfRel();
        return CollectionModel.of(patrocinadorModels, link);
    }

    @GetMapping("/{id}")
    public EntityModel<GetPatrocinadorModel> getPatrocinadorById(@PathVariable Long id) {
        GetPatrocinadorModel patrocinador = patrocinadorService.getPatrocinadorById(id);
        if (patrocinador == null) {
            return EntityModel.of(null);
        }
        return EntityModel.of(patrocinador,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrocinadorController.class).getPatrocinadorById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrocinadorController.class).getAll(null, null)).withRel("patrocinadores"));
    }


}
