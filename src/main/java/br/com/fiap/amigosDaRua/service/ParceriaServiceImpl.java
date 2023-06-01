package br.com.fiap.amigosDaRua.service;

import br.com.fiap.amigosDaRua.entities.Parceria;
import br.com.fiap.amigosDaRua.repositories.ParceriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParceriaServiceImpl implements ParceriaService{
    @Autowired
    private ParceriaRepository parceriaRepository;

    public void createParceria(Parceria parceria){
        parceriaRepository.save(parceria);
    }
}
