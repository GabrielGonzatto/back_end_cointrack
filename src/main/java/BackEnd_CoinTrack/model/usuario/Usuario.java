package BackEnd_CoinTrack.model.usuario;

import BackEnd_CoinTrack.model.usuario.DTOs.LoginUsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O nome deve ser preenchido!")
    private String nome;

    private String apelido;

    @Email
    @Column(unique = true)
    @NotBlank(message = "O email deve ser preenchido!")
    private String email;

    @NotBlank(message = "A senha deve ser preenchida!")
    private String senha;

    public Boolean validaLogin (LoginUsuarioDTO loginUsuario) {
        if (this.senha.equals(loginUsuario.senha())) {
            return true;
        }
        return false;
    }

    public Usuario() {
    }

    public Usuario(String nome, String apelido, String email, String senha) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String apelido, String email) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotBlank String getApelido() {
        return apelido;
    }

    public void setApelido(@NotBlank String apelido) {
        this.apelido = apelido;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotBlank String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank String senha) {
        this.senha = senha;
    }
}
