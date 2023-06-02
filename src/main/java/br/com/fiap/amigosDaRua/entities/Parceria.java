package br.com.fiap.amigosDaRua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_ar_parceria")
public class Parceria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_parceria", precision = 38, scale = 0, nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_patrocinador", foreignKey = @ForeignKey(name = "fk_patrocinador_parceria"))
    private Patrocinador patrocinador;

    @ManyToOne
    @JoinColumn(name = "id_evento", foreignKey = @ForeignKey(name = "fk_evento_parceria"))
    private Evento evento;
}
