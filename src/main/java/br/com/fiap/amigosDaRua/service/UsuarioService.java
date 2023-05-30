package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.models.GetUsuarioModel;
import br.com.fiap.amigosDaRua.models.UpdateUsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    Page<GetUsuarioModel> getAllUsuarios(Pageable pageable);
    GetUsuarioModel getUsuarioById(Long id);
    Page<GetUsuarioModel> getUsuariosByNome(String nome, Pageable pageable);
    void updateUsuario(Long id, UpdateUsuarioModel model);
}

