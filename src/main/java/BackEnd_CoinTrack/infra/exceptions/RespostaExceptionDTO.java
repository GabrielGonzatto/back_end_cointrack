package BackEnd_CoinTrack.infra.exceptions;

import org.springframework.http.HttpStatus;

public record RespostaExceptionDTO(HttpStatus statusName, Integer statusCode, String mensagem) {
}
