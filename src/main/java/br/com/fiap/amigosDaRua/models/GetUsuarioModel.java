package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsuarioModel extends RepresentationModel<GetUsuarioModel> {
    private Long id;
    private String nome;
    private BigInteger telefone;
    private String email;
}

