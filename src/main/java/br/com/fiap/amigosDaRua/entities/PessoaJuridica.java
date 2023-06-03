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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pessoa_juridica", precision = 38, scale = 0)
    private Long id;

    @Column(name = "nr_cnpj", precision = 14, scale = 0)
    private BigInteger cnpj;

    @Column(name = "nm_fantasia", precision = 255)
    private String nomeFantasia;

    @Column(name = "nr_insc_estadual", precision = 14, scale = 0)
    private BigInteger inscricaoMunicipal;

    @Column(name = "ds_ramo_atividade", precision = 255)
    private String ramoAtividade;

    @OneToOne
    @JoinColumn(name = "id_responsavel")
    Usuario usuario;
}
