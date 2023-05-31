package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.models.InsertLocalModel;
import org.springframework.stereotype.Service;

@Service
public interface LocalService {
    void createLocal(InsertLocalModel insertLocalModel);
}
