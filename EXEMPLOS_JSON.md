# Exemplos de Raw JSON para Todos os Endpoints

## **USUÁRIOS** (`/usuarios`)

### POST /usuarios - Criar Usuário
```json
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "dataInscricao": "2026-03-25"
}
```

### PUT /usuarios/{id} - Atualizar Usuário
```json
{
  "nome": "João Silva Atualizado",
  "email": "joao.novo@example.com",
  "dataInscricao": "2026-03-25"
}
```

---

## **ARTISTAS** (`/artistas`)

### POST /artistas - Criar Artista
```json
{
  "nome": "Queen"
}
```

### Exemplo 2 - POST /artistas
```json
{
  "nome": "The Beatles"
}
```

### Exemplo 3 - POST /artistas
```json
{
  "nome": "David Bowie"
}
```

### PUT /artistas/{id} - Atualizar Artista
```json
{
  "nome": "Queen - Atualizado"
}
```

---

## **ESTILOS** (`/estilos`)

### POST /estilos - Criar Estilo
```json
{
  "nome": "Rock"
}
```

### Exemplo 2 - POST /estilos
```json
{
  "nome": "Pop"
}
```

### Exemplo 3 - POST /estilos
```json
{
  "nome": "Jazz"
}
```

### Exemplo 4 - POST /estilos
```json
{
  "nome": "Hip Hop"
}
```

### PUT /estilos/{id} - Atualizar Estilo
```json
{
  "nome": "Rock & Roll"
}
```

---

## **PRODUTORAS** (`/produtoras`)

### POST /produtoras - Criar Produtora
```json
{
  "nome": "EMI Records"
}
```

### Exemplo 2 - POST /produtoras
```json
{
  "nome": "Universal Music"
}
```

### Exemplo 3 - POST /produtoras
```json
{
  "nome": "Sony Music"
}
```

### PUT /produtoras/{id} - Atualizar Produtora
```json
{
  "nome": "EMI Records - Atualizado"
}
```

---

## **ÁLBUNS** (`/albums`)

### POST /albums - Criar Álbum
```json
{
  "nome": "A Night at the Opera",
  "dataLancamento": "1975-11-21",
  "produtoraId": 1,
  "artistaId": 1
}
```

### Exemplo 2 - POST /albums
```json
{
  "nome": "Bohemian Rhapsody",
  "dataLancamento": "1975-10-31",
  "produtoraId": 1,
  "artistaId": 1
}
```

### Exemplo 3 - POST /albums
```json
{
  "nome": "The Wall",
  "dataLancamento": "1979-11-30",
  "produtoraId": 2,
  "artistaId": 2
}
```

### Exemplo 4 - POST /albums
```json
{
  "nome": "Abbey Road",
  "dataLancamento": "1969-09-26",
  "produtoraId": 3,
  "artistaId": 2
}
```

---

## **MÚSICAS** (`/musicas`)

### POST /musicas - Criar Música
```json
{
  "nome": "Bohemian Rhapsody",
  "dataLancamento": "1975-10-31",
  "albumId": 1,
  "artistaId": 1,
  "produtoraId": 1,
  "estiloId": 1
}
```

### Exemplo 2 - POST /musicas
```json
{
  "nome": "Another One Bites the Dust",
  "dataLancamento": "1980-08-22",
  "albumId": 2,
  "artistaId": 1,
  "produtoraId": 1,
  "estiloId": 1
}
```

### Exemplo 3 - POST /musicas
```json
{
  "nome": "Come Together",
  "dataLancamento": "1969-09-26",
  "albumId": 3,
  "artistaId": 2,
  "produtoraId": 2,
  "estiloId": 1
}
```

### Exemplo 4 - POST /musicas
```json
{
  "nome": "Imagine",
  "dataLancamento": "1971-09-09",
  "albumId": 4,
  "artistaId": 2,
  "produtoraId": 3,
  "estiloId": 2
}
```

### PUT /musicas/{id} - Atualizar Música
```json
{
  "nome": "Bohemian Rhapsody - Remasterizado",
  "dataLancamento": "1975-10-31",
  "albumId": 1,
  "artistaId": 1,
  "produtoraId": 1,
  "estiloId": 1
}
```

---

## **FAVORITAS** (`/favoritas`)

### POST /favoritas - Criar Favorita
```json
{
  "usuarioId": 1,
  "musicaId": 5,
  "dataFavoritada": "2026-03-25"
}
```

### Exemplo 2 - POST /favoritas
```json
{
  "usuarioId": 1,
  "musicaId": 1,
  "dataFavoritada": "2026-03-25"
}
```

### Exemplo 3 - POST /favoritas
```json
{
  "usuarioId": 2,
  "musicaId": 3,
  "dataFavoritada": "2026-03-24"
}
```

### Exemplo 4 - POST /favoritas
```json
{
  "usuarioId": 3,
  "musicaId": 2,
  "dataFavoritada": "2026-03-23"
}
```

### PUT /favoritas/{id} - Atualizar Favorita
```json
{
  "usuarioId": 1,
  "musicaId": 5,
  "dataFavoritada": "2026-03-26"
}
```

### DELETE /favoritas/usuario/{usuarioId} - Remover todas as Favoritas de um Usuário
```json
{}
```

> Este endpoint não exige body na requisição. O JSON acima é apenas ilustrativo; basta informar o `usuarioId` na URL.

### DELETE /favoritas/usuario/{usuarioId}/musica/{musicaId} - Remover uma Favorita específica do Usuário
```json
{}
```

> Este endpoint também não exige body. Informe `usuarioId` e `musicaId` diretamente na URL.

---

## **Referência Rápida de GET Endpoints**

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Obtém um usuário específico |
| GET | `/artistas` | Lista todos os artistas |
| GET | `/artistas/{id}` | Obtém um artista específico |
| GET | `/estilos` | Lista todos os estilos |
| GET | `/estilos/{id}` | Obtém um estilo específico |
| GET | `/produtoras` | Lista todas as produtoras |
| GET | `/produtoras/{id}` | Obtém uma produtora específica |
| GET | `/albums` | Lista todos os álbuns |
| GET | `/albums/{id}` | Obtém um álbum específico |
| GET | `/musicas` | Lista todas as músicas |
| GET | `/musicas/{id}` | Obtém uma música específica |
| GET | `/favoritas` | Lista todas as favoritas |
| GET | `/favoritas/{id}` | Obtém uma favorita específica |
| GET | `/favoritas/usuario/{usuarioId}` | Lista favoritas de um usuário |

## **Exemplos com DELETE por Usuário**

### Remover todas as favoritas de um usuário com CURL
```bash
curl -X DELETE http://localhost:8080/favoritas/usuario/1
```

### Remover uma favorita específica de um usuário com CURL
```bash
curl -X DELETE http://localhost:8080/favoritas/usuario/1/musica/2
```

---

## **Exemplos com CURL**

### Criar Usuário com CURL
```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@example.com",
    "dataInscricao": "2026-03-25"
  }'
```

### Criar Artista com CURL
```bash
curl -X POST http://localhost:8080/artistas \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Queen"
  }'
```

### Criar Música com CURL
```bash
curl -X POST http://localhost:8080/musicas \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Bohemian Rhapsody",
    "dataLancamento": "1975-10-31",
    "albumId": 1,
    "artistaId": 1,
    "produtoraId": 1,
    "estiloId": 1
  }'
```

### Criar Favorita com CURL
```bash
curl -X POST http://localhost:8080/favoritas \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "musicaId": 5,
    "dataFavoritada": "2026-03-25"
  }'
```

### Atualizar Usuário com CURL
```bash
curl -X PUT http://localhost:8080/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva Atualizado",
    "email": "joao.novo@example.com",
    "dataInscricao": "2026-03-25"
  }'
```

### Obter Usuário com CURL
```bash
curl -X GET http://localhost:8080/usuarios/1
```

### Deletar Usuário com CURL
```bash
curl -X DELETE http://localhost:8080/usuarios/1
```

---

## **Status Codes Esperados**

| Operação | Status | Descrição |
|----------|--------|-----------|
| POST (Sucesso) | `201 Created` | Recurso criado com sucesso |
| GET (Sucesso) | `200 OK` | Dados retornados com sucesso |
| PUT (Sucesso) | `200 OK` | Recurso atualizado com sucesso |
| DELETE (Sucesso) | `204 No Content` | Recurso deletado com sucesso |
| Erro de Validação | `400 Bad Request` | Dados inválidos ou ID não encontrado |
| Não Encontrado | `404 Not Found` | Recurso não existe |
| Erro Servidor | `500 Internal Server Error` | Erro no processamento |

---

## **Notas Importantes**

✅ **Validações Ativas:**
- Campos marcados com `@NotNull` e `@NotBlank` são obrigatórios
- Sempre verifique as dependências entre tabelas (ex: musicaId, artistaId, etc devem existir)

✅ **Headers Recomendados:**
```
Content-Type: application/json
```

✅ **Ferramenta de Teste Recomendada:**
- **Postman** - Interface gráfica intuitiva
- **Insomnia** - Alternativa moderna ao Postman
- **curl** - Linha de comando
- **Thunder Client** - Extensão VS Code

