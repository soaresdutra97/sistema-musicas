package com.projeto.projeto.infrastructure.adapter.inbound.rest.favorita;

import com.projeto.projeto.application.favorita.dto.FavoritaRequest;
import com.projeto.projeto.application.favorita.dto.FavoritaResponse;
import com.projeto.projeto.application.favorita.port.in.AtualizarFavoritaUseCase;
import com.projeto.projeto.application.favorita.port.in.BuscarFavoritaPorIdUseCase;
import com.projeto.projeto.application.favorita.port.in.CriarFavoritaUseCase;
import com.projeto.projeto.application.favorita.port.in.ListarFavoritasPorUsuarioUseCase;
import com.projeto.projeto.application.favorita.port.in.ListarFavoritasUseCase;
import com.projeto.projeto.application.favorita.port.in.RemoverFavoritaPorUsuarioEMusicaUseCase;
import com.projeto.projeto.application.favorita.port.in.RemoverFavoritaUseCase;
import com.projeto.projeto.application.favorita.port.in.RemoverFavoritasPorUsuarioUseCase;
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
@RequestMapping("/favoritas")
public class FavoritaController {

	private final AtualizarFavoritaUseCase atualizarFavoritaUseCase;
	private final BuscarFavoritaPorIdUseCase buscarFavoritaPorIdUseCase;
	private final CriarFavoritaUseCase criarFavoritaUseCase;
	private final ListarFavoritasUseCase listarFavoritasUseCase;
	private final ListarFavoritasPorUsuarioUseCase listarFavoritasPorUsuarioUseCase;
	private final RemoverFavoritaUseCase removerFavoritaUseCase;
	private final RemoverFavoritaPorUsuarioEMusicaUseCase removerFavoritaPorUsuarioEMusicaUseCase;
	private final RemoverFavoritasPorUsuarioUseCase removerFavoritasPorUsuarioUseCase;

	public FavoritaController(AtualizarFavoritaUseCase atualizarFavoritaUseCase,
							  BuscarFavoritaPorIdUseCase buscarFavoritaPorIdUseCase,
							  CriarFavoritaUseCase criarFavoritaUseCase,
							  ListarFavoritasUseCase listarFavoritasUseCase,
							  ListarFavoritasPorUsuarioUseCase listarFavoritasPorUsuarioUseCase,
							  RemoverFavoritaUseCase removerFavoritaUseCase,
							  RemoverFavoritaPorUsuarioEMusicaUseCase removerFavoritaPorUsuarioEMusicaUseCase,
							  RemoverFavoritasPorUsuarioUseCase removerFavoritasPorUsuarioUseCase) {
		this.atualizarFavoritaUseCase = atualizarFavoritaUseCase;
		this.buscarFavoritaPorIdUseCase = buscarFavoritaPorIdUseCase;
		this.criarFavoritaUseCase = criarFavoritaUseCase;
		this.listarFavoritasUseCase = listarFavoritasUseCase;
		this.listarFavoritasPorUsuarioUseCase = listarFavoritasPorUsuarioUseCase;
		this.removerFavoritaUseCase = removerFavoritaUseCase;
		this.removerFavoritaPorUsuarioEMusicaUseCase = removerFavoritaPorUsuarioEMusicaUseCase;
		this.removerFavoritasPorUsuarioUseCase = removerFavoritasPorUsuarioUseCase;
	}

	@PostMapping
	public ResponseEntity<FavoritaResponse> criar(@Valid @RequestBody FavoritaRequest request) {
		FavoritaResponse response = criarFavoritaUseCase.execute(request);
		return ResponseEntity.created(URI.create("/favoritas/" + response.id())).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FavoritaResponse> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(buscarFavoritaPorIdUseCase.execute(id));
	}

	@GetMapping
	public ResponseEntity<List<FavoritaResponse>> listar() {
		return ResponseEntity.ok(listarFavoritasUseCase.execute());
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<FavoritaResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
		return ResponseEntity.ok(listarFavoritasPorUsuarioUseCase.execute(usuarioId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<FavoritaResponse> atualizar(@PathVariable Long id,
													  @Valid @RequestBody FavoritaRequest request) {
		return ResponseEntity.ok(atualizarFavoritaUseCase.execute(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		removerFavoritaUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/usuario/{usuarioId}/musica/{musicaId}")
	public ResponseEntity<Void> removerPorUsuarioEMusica(@PathVariable Long usuarioId,
										@PathVariable Long musicaId) {
		removerFavoritaPorUsuarioEMusicaUseCase.execute(usuarioId, musicaId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/usuario/{usuarioId}")
	public ResponseEntity<Void> removerPorUsuario(@PathVariable Long usuarioId) {
		removerFavoritasPorUsuarioUseCase.execute(usuarioId);
		return ResponseEntity.noContent().build();
	}
}


