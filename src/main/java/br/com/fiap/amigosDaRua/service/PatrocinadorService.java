package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Patrocinador;
import br.com.fiap.amigosDaRua.models.GetPatrocinadorModel;
import br.com.fiap.amigosDaRua.models.InsertPatrocinadorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PatrocinadorService {
    Patrocinador createPatrocinador(InsertPatrocinadorModel insertPatrocinadorModel);
    Page<GetPatrocinadorModel> getAllPatrocinadores(Pageable pageable);
    Page<GetPatrocinadorModel> getPatrocinadoresByTipo(String tipo, Pageable pageable);
    GetPatrocinadorModel getPatrocinadorById(Long id);
}
