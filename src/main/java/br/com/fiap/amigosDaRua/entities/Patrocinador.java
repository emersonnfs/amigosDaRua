package br.com.fiap.amigosDaRua.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_ar_patrocinador")
public class Patrocinador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_patrocinador", precision = 38, scale = 0)
    private Long id;

    @Column(name = "nm_patrocinador", precision = 255)
    private String nome;

    @Column(name = "tp_patrocinador")
    private TipoPatrocinadorEnum tipo;

    @OneToMany(mappedBy = "patrocinador", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Parceria> parcerias;
}
