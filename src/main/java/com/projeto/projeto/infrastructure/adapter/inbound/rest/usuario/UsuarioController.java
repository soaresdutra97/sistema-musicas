package com.projeto.projeto.infrastructure.adapter.inbound.rest.usuario;

import com.projeto.projeto.application.usuario.dto.UsuarioRequest;
import com.projeto.projeto.application.usuario.dto.UsuarioResponse;
import com.projeto.projeto.application.usuario.port.in.AtualizarUsuarioUseCase;
import com.projeto.projeto.application.usuario.port.in.BuscarUsuarioPorIdUseCase;
import com.projeto.projeto.application.usuario.port.in.CriarUsuarioUseCase;
import com.projeto.projeto.application.usuario.port.in.ListarUsuariosUseCase;
import com.projeto.projeto.application.usuario.port.in.RemoverUsuarioUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final RemoverUsuarioUseCase removerUsuarioUseCase;

    public UsuarioController(AtualizarUsuarioUseCase atualizarUsuarioUseCase,
                             BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                             CriarUsuarioUseCase criarUsuarioUseCase,
                             ListarUsuariosUseCase listarUsuariosUseCase,
                             RemoverUsuarioUseCase removerUsuarioUseCase) {
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.removerUsuarioUseCase = removerUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = criarUsuarioUseCase.execute(request);
        return ResponseEntity.created(URI.create("/usuarios/" + response.id())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarUsuarioPorIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(listarUsuariosUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id,
                                                     @Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(atualizarUsuarioUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerUsuarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}



