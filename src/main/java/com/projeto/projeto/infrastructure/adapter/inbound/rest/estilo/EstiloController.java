package com.projeto.projeto.infrastructure.adapter.inbound.rest.estilo;

import com.projeto.projeto.application.estilo.dto.EstiloRequest;
import com.projeto.projeto.application.estilo.dto.EstiloResponse;
import com.projeto.projeto.application.estilo.port.in.AtualizarEstiloUseCase;
import com.projeto.projeto.application.estilo.port.in.BuscarEstiloPorIdUseCase;
import com.projeto.projeto.application.estilo.port.in.CriarEstiloUseCase;
import com.projeto.projeto.application.estilo.port.in.ListarEstiloUseCase;
import com.projeto.projeto.application.estilo.port.in.RemoverEstiloUseCase;
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
@RequestMapping("/estilos")
public class EstiloController {

	private final AtualizarEstiloUseCase atualizarEstiloUseCase;
	private final BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase;
	private final CriarEstiloUseCase criarEstiloUseCase;
	private final ListarEstiloUseCase listarEstiloUseCase;
	private final RemoverEstiloUseCase removerEstiloUseCase;

	public EstiloController(AtualizarEstiloUseCase atualizarEstiloUseCase,
							BuscarEstiloPorIdUseCase buscarEstiloPorIdUseCase,
							CriarEstiloUseCase criarEstiloUseCase,
							ListarEstiloUseCase listarEstiloUseCase,
							RemoverEstiloUseCase removerEstiloUseCase) {
		this.atualizarEstiloUseCase = atualizarEstiloUseCase;
		this.buscarEstiloPorIdUseCase = buscarEstiloPorIdUseCase;
		this.criarEstiloUseCase = criarEstiloUseCase;
		this.listarEstiloUseCase = listarEstiloUseCase;
		this.removerEstiloUseCase = removerEstiloUseCase;
	}

	@PostMapping
	public ResponseEntity<EstiloResponse> criar(@Valid @RequestBody EstiloRequest request) {
		EstiloResponse response = criarEstiloUseCase.execute(request);
		return ResponseEntity.created(URI.create("/estilos/" + response.id())).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstiloResponse> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(buscarEstiloPorIdUseCase.execute(id));
	}

	@GetMapping
	public ResponseEntity<List<EstiloResponse>> listar() {
		return ResponseEntity.ok(listarEstiloUseCase.execute());
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstiloResponse> atualizar(@PathVariable Long id,
													@Valid @RequestBody EstiloRequest request) {
		return ResponseEntity.ok(atualizarEstiloUseCase.execute(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		removerEstiloUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}
}


