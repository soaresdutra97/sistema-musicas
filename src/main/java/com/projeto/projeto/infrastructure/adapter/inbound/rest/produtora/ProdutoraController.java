package com.projeto.projeto.infrastructure.adapter.inbound.rest.produtora;

import com.projeto.projeto.application.produtora.dto.ProdutoraRequest;
import com.projeto.projeto.application.produtora.dto.ProdutoraResponse;
import com.projeto.projeto.application.produtora.port.in.AtualizarProdutoraUseCase;
import com.projeto.projeto.application.produtora.port.in.BuscarProdutoraPorIdUseCase;
import com.projeto.projeto.application.produtora.port.in.CriarProdutoraUseCase;
import com.projeto.projeto.application.produtora.port.in.ListarProdutorasUseCase;
import com.projeto.projeto.application.produtora.port.in.RemoverProdutoraUseCase;
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
@RequestMapping("/produtoras")
public class ProdutoraController {

    private final AtualizarProdutoraUseCase atualizarProdutoraUseCase;
    private final BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase;
    private final CriarProdutoraUseCase criarProdutoraUseCase;
    private final ListarProdutorasUseCase listarProdutorasUseCase;
    private final RemoverProdutoraUseCase removerProdutoraUseCase;

    public ProdutoraController(AtualizarProdutoraUseCase atualizarProdutoraUseCase,
                               BuscarProdutoraPorIdUseCase buscarProdutoraPorIdUseCase,
                               CriarProdutoraUseCase criarProdutoraUseCase,
                               ListarProdutorasUseCase listarProdutorasUseCase,
                               RemoverProdutoraUseCase removerProdutoraUseCase) {
        this.atualizarProdutoraUseCase = atualizarProdutoraUseCase;
        this.buscarProdutoraPorIdUseCase = buscarProdutoraPorIdUseCase;
        this.criarProdutoraUseCase = criarProdutoraUseCase;
        this.listarProdutorasUseCase = listarProdutorasUseCase;
        this.removerProdutoraUseCase = removerProdutoraUseCase;
    }

    @PostMapping
    public ResponseEntity<ProdutoraResponse> criar(@Valid @RequestBody ProdutoraRequest request) {
        ProdutoraResponse response = criarProdutoraUseCase.execute(request);
        return ResponseEntity.created(URI.create("/produtoras/" + response.id())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoraResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarProdutoraPorIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoraResponse>> listar() {
        return ResponseEntity.ok(listarProdutorasUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoraResponse> atualizar(@PathVariable Long id,
                                                       @Valid @RequestBody ProdutoraRequest request) {
        return ResponseEntity.ok(atualizarProdutoraUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerProdutoraUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}



