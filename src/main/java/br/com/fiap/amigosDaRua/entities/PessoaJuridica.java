package br.com.fiap.amigosDaRua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_ar_pessoa_juridica")
public class PessoaJuridica {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pessoa_juridica")
    private Long id;

    @Column(name = "nr_cnpj")
    private BigInteger cnpj;

    @Column(name = "nm_fantasia")
    private String nomeFantasia;

    @Column(name = "nr_insc_estadual")
    private BigInteger inscricaoMunicipal;

    @Column(name = "ds_ramo_atividade")
    private String ramoAtividade;

    @OneToOne
    @JoinColumn(name = "id_responsavel")
    Usuario usuario;
}
