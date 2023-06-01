package br.com.fiap.amigosDaRua.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLocalModel extends RepresentationModel<GetLocalModel> {
    private Long id;

    private String nome;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
