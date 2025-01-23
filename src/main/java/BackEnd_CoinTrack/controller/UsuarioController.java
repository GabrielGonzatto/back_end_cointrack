package BackEnd_CoinTrack.controller;

import BackEnd_CoinTrack.model.usuario.DTOs.*;
import BackEnd_CoinTrack.model.usuario.DTOs.respostas.RespostaLogin;
import BackEnd_CoinTrack.model.usuario.DTOs.respostas.RespostaUsuarioDTO;
import BackEnd_CoinTrack.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<RespostaLogin> login(@RequestBody @Valid LoginUsuarioDTO loginUsuario){
        return ResponseEntity.ok(this.service.login(loginUsuario));
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<RespostaUsuarioDTO> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO usuarioDTO){
        return ResponseEntity.ok(this.service.cadastrarUsuario(usuarioDTO));
    }

    @PutMapping("/editar")
    @Transactional
    public ResponseEntity<RespostaUsuarioDTO> editarUsuario(@RequestBody @Valid EditarUsuarioDTO usuarioDTO, HttpServletRequest request){
        return ResponseEntity.ok(this.service.editarUsuario(usuarioDTO, request));
    }

    @GetMapping("/listar")
    @Transactional
    public ResponseEntity<RespostaUsuarioDTO> listarDadosDoUsuario(HttpServletRequest request){
        return ResponseEntity.ok(this.service.listarDadosUsuario(request));
    }
}
