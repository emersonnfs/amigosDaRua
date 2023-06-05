package br.com.fiap.amigosDaRua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_ar_pessoa_fisica")
public class PessoaFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pessoa_fisica", precision = 38, scale = 0)
    private Long id;

    @Column(name = "nr_rg", precision = 9, scale = 0)
    private BigInteger rg;

    @Column(name = "nr_cpf", precision = 11, scale = 0)
    private BigInteger cpf;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "id_responsavel", foreignKey = @ForeignKey(name = "fk_responsavel_fisica"))
    Usuario usuario;
}
