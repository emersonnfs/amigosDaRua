package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.Patrocinador;
import br.com.fiap.amigosDaRua.entities.TipoPatrocinadorEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Long> {
    @Query("SELECT p FROM Patrocinador p WHERE p.tipo = :tipo")
    Page<Patrocinador> getPatrocinadoresByTipo(@Param("tipo") TipoPatrocinadorEnum tipo, Pageable pageable);
}
