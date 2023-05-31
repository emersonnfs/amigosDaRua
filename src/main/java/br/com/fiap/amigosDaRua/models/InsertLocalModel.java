package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertLocalModel {
    private String nome;
    private String latitude;
    private String longitude;
}
