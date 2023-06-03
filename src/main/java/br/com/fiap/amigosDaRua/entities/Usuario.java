package br.com.fiap.amigosDaRua.entities;

import br.com.fiap.amigosDaRua.models.GetUsuarioModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_ar_responsavel", indexes = {
        @Index(name = "uk_ds_email", columnList = "ds_email", unique = true)
    })
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_responsavel", precision = 38, scale = 0)
    private Long id;

    @Column(name = "nm_responsavel", precision = 255)
    private String nome;

    @Column(name = "nr_telefone", precision = 14, scale = 0)
    private BigInteger telefone;

    @Column(name = "ds_email", precision = 255 )
    private String email;

    @Column(name = "ds_senha", precision = 255)
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private PessoaFisica pessoaFisica;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private PessoaJuridica pessoaJuridica;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Evento> eventos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
