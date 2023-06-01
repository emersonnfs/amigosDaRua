package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    @Query("SELECT l FROM Local l WHERE lower(l.nome) LIKE %:nome%")
    Page<Local> findByNomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);
    @Query("SELECT l FROM Local l WHERE l.latitude = :latitude AND l.longitude = :longitude")
    Page<Local> getLocalByLatitudeAndLongitude(@Param("latitude") BigDecimal latitude,
                                               @Param("longitude") BigDecimal longitude, Pageable pageable);
}
