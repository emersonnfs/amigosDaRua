package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertPessoaJuridica {
    private String cnpj;
    private String nomeFantasia;
    private String inscricaoMunicipal;
    private String ramoAtividade;
    private Long idUsuario;
}
