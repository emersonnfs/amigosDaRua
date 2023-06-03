package br.com.fiap.amigosDaRua.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_ar_evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_evento", precision = 38, scale = 0)
    private Long id;

    @Column(name = "nm_evento", precision = 255)
    private String nome;

    @Column(name = "hora_inicio")
    private LocalDateTime horaInicio;

    @Column(name = "hora_fim")
    private LocalDateTime horaFim;

    @Column(name = "tp_evento")
    private TipoEventoEnum tipo;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_local")
    private Local local;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Parceria> parcerias;
}
