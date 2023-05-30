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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pessoa_fisica")
    private Long id;

    @Column(name = "nr_rg")
    private BigInteger rg;

    @Column(name = "nr_cpf")
    private BigInteger cpf;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "id_responsavel")
    Usuario usuario;
}
