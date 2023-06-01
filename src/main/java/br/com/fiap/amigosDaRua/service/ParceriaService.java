package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Parceria;
import br.com.fiap.amigosDaRua.models.InsertEventoModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ParceriaService {
    void createParceria(Parceria parceria);


}
