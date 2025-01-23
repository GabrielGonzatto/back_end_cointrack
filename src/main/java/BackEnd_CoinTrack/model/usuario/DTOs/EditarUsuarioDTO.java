package BackEnd_CoinTrack.model.usuario.DTOs;


import java.util.Optional;

public record EditarUsuarioDTO(String nome, String apelido, String senha, String novaSenha) {

}
