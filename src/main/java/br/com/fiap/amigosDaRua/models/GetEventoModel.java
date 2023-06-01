package br.com.fiap.amigosDaRua.models;

import br.com.fiap.amigosDaRua.entities.TipoEventoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventoModel {
    private Long id;
    private String nome;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private TipoEventoEnum tipo;
    private GetUsuarioModel usuario;
    private GetLocalModel local;
    private List<GetPatrocinadorModel> patrocinadores;
}
