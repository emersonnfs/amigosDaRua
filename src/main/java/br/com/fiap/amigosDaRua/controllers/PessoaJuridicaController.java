package br.com.fiap.amigosDaRua.controllers;

import br.com.fiap.amigosDaRua.models.InsertPessoaJuridica;
import br.com.fiap.amigosDaRua.service.PessoaJuridicaService;
import br.com.fiap.amigosDaRua.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoajuridica")
public class PessoaJuridicaController {
    @Autowired
    PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid InsertPessoaJuridica model){
        pessoaJuridicaService.createPessoaJuridica(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping("/{id}")
    public EntityModel<Object> getById(@PathVariable Long id){
        var pessoaJuridica = pessoaJuridicaService.getPessoaJuridicaById(id);
        if(pessoaJuridica == null){
            return EntityModel.of(null);
        }
        return EntityModel.of(pessoaJuridica,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaJuridicaController.class)
                        .getById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).
                        getUsuarioById(pessoaJuridica.getUsuario().getId())).withRel("usuario"));
    }
}
