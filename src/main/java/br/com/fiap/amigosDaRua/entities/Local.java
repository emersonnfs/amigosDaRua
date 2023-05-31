package br.com.fiap.amigosDaRua.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_ar_local")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_local")
    private Long id;

    @Column(name = "nm_local")
    private String nome;

    @Column(name = "nr_latitude", precision = 11, scale = 8)
    private BigDecimal latitude;

    @Column(name = "nr_longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Evento> eventos;
}
