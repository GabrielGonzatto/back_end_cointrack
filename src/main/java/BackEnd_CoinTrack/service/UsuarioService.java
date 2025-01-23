package BackEnd_CoinTrack.service;

import BackEnd_CoinTrack.infra.security.TokenService;
import BackEnd_CoinTrack.model.usuario.DTOs.CadastrarUsuarioDTO;
import BackEnd_CoinTrack.model.usuario.DTOs.EditarUsuarioDTO;
import BackEnd_CoinTrack.model.usuario.DTOs.LoginUsuarioDTO;
import BackEnd_CoinTrack.model.usuario.DTOs.respostas.RespostaLogin;
import BackEnd_CoinTrack.model.usuario.DTOs.respostas.RespostaUsuarioDTO;
import BackEnd_CoinTrack.model.usuario.Usuario;
import BackEnd_CoinTrack.model.usuario.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public UsuarioService(UsuarioRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public RespostaLogin login (LoginUsuarioDTO loginUsuario) {
        Usuario usuario = this.repository.findByEmail(loginUsuario.email());

        if (usuario == null || !usuario.validaLogin(loginUsuario)) {
            throw new BadCredentialsException("Email ou senha incorretos!");
        }

        return tokenService.gerarToken(usuario.getId());
    }

    public RespostaUsuarioDTO cadastrarUsuario(CadastrarUsuarioDTO usuarioDTO) {
        this.repository.save(usuarioDTO.toUsuario());
        return new RespostaUsuarioDTO(HttpStatus.OK, HttpStatus.OK.value(), "Usuário criado com sucesso!", null);
    }

    public RespostaUsuarioDTO editarUsuario (EditarUsuarioDTO usuarioDTO, HttpServletRequest request) {
            Usuario usuarioBanco = this.repository.getReferenceById(Long.valueOf(this.tokenService.recuperarIdDoToken(request)));

            if (usuarioBanco.getSenha().equals(usuarioBanco.getSenha())) {
                usuarioBanco.setNome(usuarioDTO.nome());
                usuarioBanco.setApelido(usuarioDTO.apelido());

                if (usuarioDTO.novaSenha() != null) {
                    usuarioBanco.setSenha(usuarioDTO.novaSenha());
                } else {
                    usuarioBanco.setSenha(usuarioDTO.senha());
                }
            }

            this.repository.save(usuarioBanco);

            return new RespostaUsuarioDTO(HttpStatus.OK, HttpStatus.OK.value(), "Usuário editado com sucesso!", new Usuario(usuarioBanco.getNome(), usuarioBanco.getApelido(), usuarioBanco.getEmail()));
    }

    public RespostaUsuarioDTO listarDadosUsuario (HttpServletRequest request) {
        Usuario usuario = this.repository.getReferenceById(Long.valueOf(this.tokenService.recuperarIdDoToken(request)));
        return new RespostaUsuarioDTO(HttpStatus.OK, HttpStatus.OK.value(), "Dados listados com sucesso!", new Usuario(usuario.getNome(), usuario.getApelido(), usuario.getEmail()));
    }
}
