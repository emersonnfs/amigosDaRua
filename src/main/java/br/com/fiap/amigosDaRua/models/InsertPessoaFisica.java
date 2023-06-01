package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertPessoaFisica {
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private String idUsuario;
}
