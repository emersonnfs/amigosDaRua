package br.com.fiap.amigosDaRua.repositories;

import br.com.fiap.amigosDaRua.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM tb_ar_responsavel WHERE ds_email = ?1", nativeQuery = true)
    Optional<Usuario> findByEmail(String username);
    @Query(value = "SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))",
            countQuery = "SELECT COUNT(u) FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Usuario> findByNomeIgnoreCaseContaining(@Param("nome") String nome, Pageable pageable);

}
