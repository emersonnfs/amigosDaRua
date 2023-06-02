package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
    @Query("SELECT e FROM Evento e WHERE e.horaFim > :currentDateTime")
    Page<Evento> findAllByHoraFimAfterCurrentDateTime(LocalDateTime currentDateTime, Pageable pageable);

    @Query("SELECT e FROM Evento e WHERE e.horaFim <= :currentDateTime")
    Page<Evento> findAllByHoraFimBeforeCurrentDateTime(LocalDateTime currentDateTime, Pageable pageable);
}
