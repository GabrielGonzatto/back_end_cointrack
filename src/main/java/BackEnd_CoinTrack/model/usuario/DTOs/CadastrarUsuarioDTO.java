package BackEnd_CoinTrack.model.usuario.DTOs;

import BackEnd_CoinTrack.model.usuario.Usuario;

public record CadastrarUsuarioDTO(String nome, String apelido, String email, String senha) {

    public Usuario toUsuario () {
        return new Usuario(this.nome, this.apelido, this.email, this.senha);
    }

}