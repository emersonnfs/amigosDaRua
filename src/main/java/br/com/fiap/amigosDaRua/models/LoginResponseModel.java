package br.com.fiap.amigosDaRua.models;

import br.com.fiap.amigosDaRua.entities.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseModel {
    private Token token;
    private Long idUsuario;
}
