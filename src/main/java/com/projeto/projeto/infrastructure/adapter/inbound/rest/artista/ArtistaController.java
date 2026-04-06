package com.projeto.projeto.infrastructure.adapter.inbound.rest.artista;

import com.projeto.projeto.application.artista.port.in.AtualizarArtistaUseCase;
import com.projeto.projeto.application.artista.port.in.BuscarArtistaPorIdUseCase;
import com.projeto.projeto.application.artista.port.in.CriarArtistaUseCase;
import com.projeto.projeto.application.artista.port.in.ListarArtistasUseCase;
import com.projeto.projeto.application.artista.port.in.RemoverArtistaUseCase;
import com.projeto.projeto.application.artista.dto.ArtistaRequest;
import com.projeto.projeto.application.artista.dto.ArtistaResponse;
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
@RequestMapping("/artistas")
public class ArtistaController {

	private final AtualizarArtistaUseCase atualizarArtistaUseCase;
	private final BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase;
	private final CriarArtistaUseCase criarArtistaUseCase;
	private final ListarArtistasUseCase listarArtistasUseCase;
	private final RemoverArtistaUseCase removerArtistaUseCase;

	public ArtistaController(AtualizarArtistaUseCase atualizarArtistaUseCase,
							 BuscarArtistaPorIdUseCase buscarArtistaPorIdUseCase,
							 CriarArtistaUseCase criarArtistaUseCase,
							 ListarArtistasUseCase listarArtistasUseCase,
							 RemoverArtistaUseCase removerArtistaUseCase) {
		this.atualizarArtistaUseCase = atualizarArtistaUseCase;
		this.buscarArtistaPorIdUseCase = buscarArtistaPorIdUseCase;
		this.criarArtistaUseCase = criarArtistaUseCase;
		this.listarArtistasUseCase = listarArtistasUseCase;
		this.removerArtistaUseCase = removerArtistaUseCase;
	}

	@PostMapping
	public ResponseEntity<ArtistaResponse> criar(@Valid @RequestBody ArtistaRequest request) {
		ArtistaResponse response = criarArtistaUseCase.execute(request);
		return ResponseEntity.created(URI.create("/artistas/" + response.id())).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArtistaResponse> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(buscarArtistaPorIdUseCase.execute(id));
	}

	@GetMapping
	public ResponseEntity<List<ArtistaResponse>> listar() {
		return ResponseEntity.ok(listarArtistasUseCase.execute());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ArtistaResponse> atualizar(@PathVariable Long id,
													 @Valid @RequestBody ArtistaRequest request) {
		return ResponseEntity.ok(atualizarArtistaUseCase.execute(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		removerArtistaUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

}


