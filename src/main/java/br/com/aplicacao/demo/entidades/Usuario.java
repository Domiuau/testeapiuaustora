package br.com.aplicacao.demo.entidades;


import br.com.aplicacao.demo.dto.usuario.DadosAtualizarUsuarioDTO;
import br.com.aplicacao.demo.dto.usuario.DadosUsuarioDTO;
import br.com.aplicacao.demo.dto.usuario.RegistroDTO;
import br.com.aplicacao.demo.enums.TipoDeUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Table(
        name = "usuarios",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"apelido", "username", "email", "telefone"})}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotBlank
    private String apelido;

    @Column(name = "senha")
    @NotBlank
    private String password;

    @NotBlank
    private String username;
    @NotBlank
    @Email(message = "O email deve ser válido")
    private String email;
    @NotNull
    private String telefone;
    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipoDeUsuario;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    List<Produto> anuncios = new ArrayList<>();

    

    private boolean enabled;


    public Usuario(RegistroDTO registroDTO, String senhaEncriptada) {

        this.apelido = registroDTO.apelido();
        this.username = registroDTO.nomeDeUsuario();
        this.email = registroDTO.email();
        this.tipoDeUsuario = registroDTO.tipo().getTipo().isEmpty() ? TipoDeUsuario.USER : registroDTO.tipo();
        this.password = senhaEncriptada;
        this.telefone = registroDTO.telefone();
        this.enabled = true;

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.tipoDeUsuario == TipoDeUsuario.ADMIN ?
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")) :
                List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public List<Produto> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Produto> anuncios) {
        this.anuncios = anuncios;
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
        return enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoDeUsuario getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public void atualizarDados(DadosAtualizarUsuarioDTO dadosUsuarioDTO) {

        if (!this.apelido.equals(dadosUsuarioDTO.apelido()))
            this.apelido = dadosUsuarioDTO.apelido();
        if (!this.username.equals(dadosUsuarioDTO.nomeDeUsuario()))
            this.username = dadosUsuarioDTO.nomeDeUsuario();
        if (!this.email.equals(dadosUsuarioDTO.email()))
            this.email = dadosUsuarioDTO.email();
        if (!new BCryptPasswordEncoder().matches(dadosUsuarioDTO.senha(), this.password) && !dadosUsuarioDTO.senha().isEmpty())
            this.password = new BCryptPasswordEncoder().encode(dadosUsuarioDTO.senha());
        if (!this.telefone.equals(dadosUsuarioDTO.telefone()))
            this.telefone = dadosUsuarioDTO.telefone();

    }



    public void desativar() {

        this.enabled = false;
    }
}
