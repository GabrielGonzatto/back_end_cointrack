package BackEnd_CoinTrack.model.usuario.DTOs.respostas;

import BackEnd_CoinTrack.model.usuario.Usuario;
import org.springframework.http.HttpStatus;

public record RespostaUsuarioDTO(HttpStatus statusName, Integer statusCode, String mensagem, Usuario usuario) {
}
