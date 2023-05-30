package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPessoaFisicaModel extends RepresentationModel<GetPessoaFisicaModel> {
    private Long id;
    private LocalDate dataNascimento;
    private GetUsuarioModel usuario;
}
