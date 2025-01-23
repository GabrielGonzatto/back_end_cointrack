package BackEnd_CoinTrack.infra.security;

import BackEnd_CoinTrack.model.usuario.DTOs.respostas.RespostaLogin;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public RespostaLogin gerarToken (Long id) {
        var tempo_agora = Instant.now();
        var expiracao_token = 7200L;

        var claims = JwtClaimsSet.builder()
                .issuer("CoinTrack")
                .subject(id.toString())
                .issuedAt(tempo_agora)
                .expiresAt(tempo_agora.plusSeconds(expiracao_token))
                .build();


        var token_jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new RespostaLogin(token_jwt, expiracao_token);
    }

    public String recuperarIdDoToken (HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " do início
            Jwt jwt = jwtDecoder.decode(token);
            return jwt.getSubject(); // Extrai a claim "subject" (ID)
        } else {
            throw new IllegalArgumentException("Token JWT não encontrado no cabeçalho Authorization");
        }
    }

}
