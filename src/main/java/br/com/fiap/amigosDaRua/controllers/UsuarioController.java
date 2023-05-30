package br.com.fiap.amigosDaRua.controllers;

import br.com.fiap.amigosDaRua.entities.Credencial;
import br.com.fiap.amigosDaRua.entities.Usuario;
import br.com.fiap.amigosDaRua.entities.factories.UsuarioMapperFactory;
import br.com.fiap.amigosDaRua.models.GetUsuarioModel;
import br.com.fiap.amigosDaRua.models.SenhaAtualizacaoRequest;
import br.com.fiap.amigosDaRua.models.UpdateUsuarioModel;
import br.com.fiap.amigosDaRua.repositories.UsuarioRepository;
import br.com.fiap.amigosDaRua.service.TokenService;
import br.com.fiap.amigosDaRua.service.UsuarioService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapperFactory usuarioMapperFactory;

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/api/usuario/{id}/atualizar-senha")
    public ResponseEntity<String> atualizarSenhaUsuario(
            @PathVariable Long id,
            @RequestBody SenhaAtualizacaoRequest request) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String senhaAntiga = request.getSenhaAntiga();
        String novaSenha = request.getSenhaNova();

        if (encoder.matches(senhaAntiga, usuario.getSenha())) {
            String senhaCriptografada = encoder.encode(novaSenha);
            usuario.setSenha(senhaCriptografada);
            repository.save(usuario);
            return ResponseEntity.ok("Senha atualizada com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Senha antiga incorreta");
        }
    }

    @GetMapping("/api/usuario")
    public CollectionModel<EntityModel<GetUsuarioModel>> getAllUsuarios(
            @RequestParam(required = false) String nome,
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 5)
            Pageable pageable) {

        Page<GetUsuarioModel> usuariosPage;
        if (nome != null) {
            usuariosPage = usuarioService.getUsuariosByNome(nome, pageable);
        } else {
            usuariosPage = usuarioService.getAllUsuarios(pageable);
        }

        List<EntityModel<GetUsuarioModel>> usuarioModels = usuariosPage.stream()
                .map(usuario -> EntityModel.of(usuario,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                                .getUsuarioById(usuario.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                                .getAllUsuarios(null, pageable)).withRel("usuarios")))
                .collect(Collectors.toList());

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .getAllUsuarios(null, pageable)).withSelfRel();

        return CollectionModel.of(usuarioModels, link);
    }

    @GetMapping("/api/usuario/{id}")
    public EntityModel<GetUsuarioModel> getUsuarioById(@PathVariable Long id) {
        GetUsuarioModel usuario = usuarioService.getUsuarioById(id);

        return EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                        .getUsuarioById(usuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                        .getAllUsuarios(null,null)).withRel("usuarios"));
    }

    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody @Valid UpdateUsuarioModel model) {
        usuarioService.updateUsuario(id, model);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }
}