package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Usuario;
import br.com.fiap.amigosDaRua.entities.factories.UsuarioMapperFactory;
import br.com.fiap.amigosDaRua.models.GetUsuarioModel;
import br.com.fiap.amigosDaRua.models.UpdateUsuarioModel;
import br.com.fiap.amigosDaRua.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapperFactory usuarioMapperFactory;

    @Override
    public Page<GetUsuarioModel> getAllUsuarios(Pageable pageable) {
        Page<Usuario> usuariosPage = usuarioRepository.findAll(pageable);
        return usuariosPage.map(this::convertToGetUsuarioModel);
    }

    @Override
    public GetUsuarioModel getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return convertToGetUsuarioModel(usuario);
    }

    @Override
    public Page<GetUsuarioModel> getUsuariosByNome(String nome, Pageable pageable) {
        Page<Usuario> usuariosPage = usuarioRepository.findByNomeIgnoreCaseContaining(nome, pageable);
        return usuariosPage.map(this::convertToGetUsuarioModel);
    }

    @Override
    public void updateUsuario(Long id, UpdateUsuarioModel updateUsuarioModel) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuarioRepository.save(usuarioMapperFactory.updateEntityFromModel(updateUsuarioModel, usuario));
    }

    private GetUsuarioModel convertToGetUsuarioModel(Usuario usuario) {
        return usuarioMapperFactory.createModelFromEntity(usuario);
    }
}

