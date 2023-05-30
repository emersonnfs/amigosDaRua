package br.com.fiap.amigosDaRua.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPessoaJuridicaModel extends RepresentationModel<GetPessoaJuridicaModel> {
    private Long id;
    private String nomeFantasia;
    private String ramoAtividade;
    private GetUsuarioModel usuario;
}

