package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.Parceria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParceriaRepository extends JpaRepository<Parceria, Long> {
    @Query("SELECT p FROM Parceria p WHERE p.evento.id = :id")
    List<Parceria> findByEventoId(@Param("id") Long id);
}
