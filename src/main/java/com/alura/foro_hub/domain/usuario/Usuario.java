package com.alura.foro_hub.domain.usuario;

import com.alura.foro_hub.domain.usuario.dto.DatosActualizacionUsuario;
import com.alura.foro_hub.domain.usuario.dto.DatosRegistroUsuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String contrasena;
    @Column(nullable = false)
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;

    public Usuario(DatosRegistroUsuario datos) {
        this.login = datos.login();
        this.nombre = datos.nombre();
        this.contrasena = datos.contrasena();
        this.perfil = datos.perfil();
    }

    public void actualizarInformaciones(@Valid DatosActualizacionUsuario datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.perfil() != null) {
            this.perfil = datos.perfil();
        }
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
    }

    @Override
    //public @Nullable String getPassword() {
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return login;
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
