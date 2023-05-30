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
    private BigInteger rg;
    private BigInteger cpf;
    private LocalDate dataNascimento;
    private Long idUsuario;
}
