package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SenhaAtualizacaoRequest {
    private String id;
    private String senhaAntiga;
    private String senhaNova;
}
