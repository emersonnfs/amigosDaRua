package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.models.GetLocalModel;
import br.com.fiap.amigosDaRua.models.InsertLocalModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface LocalService {
    void createLocal(InsertLocalModel insertLocalModel);

    Page<GetLocalModel> getAllLocais(Pageable pageable);

    Page<GetLocalModel> getLocaisByNome(String nome, Pageable pageable);

    GetLocalModel getLocalById(Long id);

    Page<GetLocalModel> getLocaisByLatitudeLongitude(String latitude, String longitude, Pageable pageable);
}
