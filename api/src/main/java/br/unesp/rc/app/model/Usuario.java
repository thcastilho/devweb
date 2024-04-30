package br.unesp.rc.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//@MappedSuperclass
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String login;
    private String email;
    private String senha;
    private UserRole role;

    @Column(name = "foto_perfil", nullable = true, length = 30)
    private String fotoPerfil;

	@OneToMany(mappedBy = "usuarioResposta", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioPost", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioAvaliacao", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuarioGenero", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Genero> generosCriados = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
        name = "comentarios_likes",
        uniqueConstraints = @UniqueConstraint (
            columnNames = {"usuario_id", "comentario_id"},
            name = "unique_usuario_like"
        ),
        joinColumns = @JoinColumn (
            name = "usuario_id",
            referencedColumnName = "id",
            table = "usuarios",
            unique = false
        ),
        inverseJoinColumns = @JoinColumn (
            name = "comentario_id",
            referencedColumnName = "id",
            table = "comentarios",
            unique = false
        )
    )
    private Set<Comentario> likes = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
        name = "comentarios_dislikes",
        uniqueConstraints = @UniqueConstraint (
            columnNames = {"usuario_id", "comentario_id"},
            name = "unique_usuario_dislike"
        ),
        joinColumns = @JoinColumn (
            name = "usuario_id",
            referencedColumnName = "id",
            table = "usuarios",
            unique = false
        ),
        inverseJoinColumns = @JoinColumn (
            name = "comentario_id",
            referencedColumnName = "id",
            table = "comentarios",
            unique = false
        )
    )
    private Set<Comentario> dislikes = new HashSet<>();

    public Usuario(String login, String senha, UserRole role){
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // O Spring Security já tem algumas roles implementadas. Repare que 
        // nesse método o retorno é uma colection, então cada usuário pode ter
        // vários papéis (roles). Por exemplo, um ADMIN é ao mesmo tempo USER
        // norma. Um CHEFE é ao mesmo tempo ADMIN e USER normal, ...
        if (this.role == UserRole.ADMIN){
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),   // Admin
                new SimpleGrantedAuthority("ROLE_USER")     // é ao mesmo tempo user normal
                );
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.getSenha();
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.getLogin();
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
    public List<Genero> getGenerosCriados() {
        return generosCriados;
    }

    public void setGenerosCriados(List<Genero> generosCriados) {
        this.generosCriados = generosCriados;
    }

    public Set<Comentario> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<Comentario> likes) {
        this.likes = likes;
    }

    public Set<Comentario> getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(Set<Comentario> dislikes) {
        this.dislikes = dislikes;
    }

    public void assignLike(Comentario comentario) {
        this.likes.add(comentario);
    }

    public void assignDislike(Comentario comentario) {
        this.dislikes.add(comentario);
    }
}
