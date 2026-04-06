package com.projeto.projeto.infrastructure.adapter.inbound.rest.album;

import com.projeto.projeto.application.album.dto.AlbumRequest;
import com.projeto.projeto.application.album.dto.AlbumResponse;
import com.projeto.projeto.application.album.port.in.AtualizarAlbumUseCase;
import com.projeto.projeto.application.album.port.in.BuscarAlbumPorIdUseCase;
import com.projeto.projeto.application.album.port.in.CriarAlbumUseCase;
import com.projeto.projeto.application.album.port.in.ListarAlbunsUseCase;
import com.projeto.projeto.application.album.port.in.RemoverAlbumUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AtualizarAlbumUseCase atualizarAlbumUseCase;
    private final BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase;
    private final CriarAlbumUseCase criarAlbumUseCase;
    private final ListarAlbunsUseCase listarAlbunsUseCase;
    private final RemoverAlbumUseCase removerAlbumUseCase;

    public AlbumController(AtualizarAlbumUseCase atualizarAlbumUseCase, BuscarAlbumPorIdUseCase buscarAlbumPorIdUseCase,
                           CriarAlbumUseCase criarAlbumUseCase, ListarAlbunsUseCase listarAlbunsUseCase,
                           RemoverAlbumUseCase removerAlbumUseCase) {
        this.atualizarAlbumUseCase = atualizarAlbumUseCase;
        this.buscarAlbumPorIdUseCase = buscarAlbumPorIdUseCase;
        this.criarAlbumUseCase = criarAlbumUseCase;
        this.listarAlbunsUseCase = listarAlbunsUseCase;
        this.removerAlbumUseCase = removerAlbumUseCase;
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> cadastrarAlbum(@Valid @RequestBody AlbumRequest request) {
        AlbumResponse response = criarAlbumUseCase.execute(request);
        return ResponseEntity.created(URI.create("/albums/" + response.id())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponse> buscarAlbumPorId(@PathVariable Long id) {
        AlbumResponse response = buscarAlbumPorIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> listarAlbuns() {
        return ResponseEntity.ok(listarAlbunsUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponse> atualizarAlbum(@PathVariable Long id, @Valid @RequestBody AlbumRequest request) {
        AlbumResponse response = atualizarAlbumUseCase.execute(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAlbum(@PathVariable Long id) {
        removerAlbumUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

