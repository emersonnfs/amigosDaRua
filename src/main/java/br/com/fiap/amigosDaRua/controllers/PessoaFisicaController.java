package br.com.fiap.amigosDaRua.controllers;

import br.com.fiap.amigosDaRua.entities.PessoaFisica;
import br.com.fiap.amigosDaRua.models.GetPessoaFisicaModel;
import br.com.fiap.amigosDaRua.models.InsertPessoaFisica;
import br.com.fiap.amigosDaRua.service.PessoaFisicaService;
import br.com.fiap.amigosDaRua.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoafisica")
public class PessoaFisicaController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PessoaFisicaService pessoaFisicaService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid InsertPessoaFisica model){
        var pessoaFisica = pessoaFisicaService.createPessoaFisica(model);
        GetPessoaFisicaModel pessoaResponse = pessoaFisicaService.getPessoaFisicaById(pessoaFisica.getId());
        return ResponseEntity.ok(pessoaResponse);
    }

    @GetMapping("/{id}")
    public EntityModel<Object> getById(@PathVariable Long id){
        var pessoaFisica = pessoaFisicaService.getPessoaFisicaById(id);
        if(pessoaFisica == null){
            return EntityModel.of(null);
        }
        return EntityModel.of(pessoaFisica,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaFisicaController.class)
                        .getById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).
                        getUsuarioById(pessoaFisica.getUsuario().getId())).withRel("usuario"));
    }

}
