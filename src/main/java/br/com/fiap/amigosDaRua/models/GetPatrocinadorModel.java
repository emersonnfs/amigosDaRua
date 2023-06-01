package br.com.fiap.amigosDaRua.models;

import br.com.fiap.amigosDaRua.entities.TipoPatrocinadorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPatrocinadorModel extends RepresentationModel<GetPatrocinadorModel> {
    private Long id;
    private String nome;
    private TipoPatrocinadorEnum tipo;
}
