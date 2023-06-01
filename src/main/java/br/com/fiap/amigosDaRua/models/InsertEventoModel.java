package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertEventoModel {
    private String nome;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private String tipo;
    private String idUsuario;
    private String idLocal;
    private List<String> idPatrocinador;
}
