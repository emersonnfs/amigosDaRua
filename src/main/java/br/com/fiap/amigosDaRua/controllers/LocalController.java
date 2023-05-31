package br.com.fiap.amigosDaRua.controllers;

import br.com.fiap.amigosDaRua.models.InsertLocalModel;
import br.com.fiap.amigosDaRua.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local")
public class LocalController {
    @Autowired
    private LocalService localService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid InsertLocalModel local){
        localService.createLocal(local);
        return ResponseEntity.status(HttpStatus.CREATED).body(local);
    }
}
