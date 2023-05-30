package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertPessoaJuridica {
    private BigInteger cnpj;
    private String nomeFantasia;
    private BigInteger inscricaoMunicipal;
    private String ramoAtividade;
    private Long idUsuario;
}
