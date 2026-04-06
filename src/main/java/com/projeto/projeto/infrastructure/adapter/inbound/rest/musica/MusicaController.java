package com.projeto.projeto.infrastructure.adapter.inbound.rest.musica;

import com.projeto.projeto.application.musica.dto.MusicaRequest;
import com.projeto.projeto.application.musica.dto.MusicaResponse;
import com.projeto.projeto.application.musica.port.in.AtualizarMusicaUseCase;
import com.projeto.projeto.application.musica.port.in.BuscarMusicaPorIdUseCase;
import com.projeto.projeto.application.musica.port.in.CriarMusicaUseCase;
import com.projeto.projeto.application.musica.port.in.ListarMusicasUseCase;
import com.projeto.projeto.application.musica.port.in.RemoverMusicaUseCase;
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
@RequestMapping("/musicas")
public class MusicaController {

	private final AtualizarMusicaUseCase atualizarMusicaUseCase;
	private final BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase;
	private final CriarMusicaUseCase criarMusicaUseCase;
	private final ListarMusicasUseCase listarMusicasUseCase;
	private final RemoverMusicaUseCase removerMusicaUseCase;

	public MusicaController(AtualizarMusicaUseCase atualizarMusicaUseCase,
							BuscarMusicaPorIdUseCase buscarMusicaPorIdUseCase,
							CriarMusicaUseCase criarMusicaUseCase,
							ListarMusicasUseCase listarMusicasUseCase,
							RemoverMusicaUseCase removerMusicaUseCase) {
		this.atualizarMusicaUseCase = atualizarMusicaUseCase;
		this.buscarMusicaPorIdUseCase = buscarMusicaPorIdUseCase;
		this.criarMusicaUseCase = criarMusicaUseCase;
		this.listarMusicasUseCase = listarMusicasUseCase;
		this.removerMusicaUseCase = removerMusicaUseCase;
	}

	@PostMapping
	public ResponseEntity<MusicaResponse> criar(@Valid @RequestBody MusicaRequest request) {
		MusicaResponse response = criarMusicaUseCase.execute(request);
		return ResponseEntity.created(URI.create("/musicas/" + response.id())).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MusicaResponse> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(buscarMusicaPorIdUseCase.execute(id));
	}

	@GetMapping
	public ResponseEntity<List<MusicaResponse>> listar() {
		return ResponseEntity.ok(listarMusicasUseCase.execute());
	}

	@PutMapping("/{id}")
	public ResponseEntity<MusicaResponse> atualizar(@PathVariable Long id,
													@Valid @RequestBody MusicaRequest request) {
		return ResponseEntity.ok(atualizarMusicaUseCase.execute(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		removerMusicaUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}


}

