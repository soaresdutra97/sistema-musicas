# Projeto API de Streaming Musical

API REST para gerenciamento de musicas, albuns, artistas, estilos, produtoras, usuarios e favoritas.

O projeto segue uma organizacao inspirada em arquitetura hexagonal (ports and adapters), separando regras de negocio, casos de uso e detalhes de infraestrutura.

## Visao geral

Este backend permite:

- cadastrar e consultar catalogo musical (musicas, albuns, artistas, estilos, produtoras);
- gerenciar usuarios;
- marcar e remover musicas favoritas por usuario;
- expor endpoints REST para integracao com clientes web/mobile.

## Stack utilizada

- Java 17
- Spring Boot 4.0.4
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Jakarta Validation
- Lombok
- Maven Wrapper (`mvnw`, `mvnw.cmd`)

## Arquitetura (hexagonal)

A estrutura principal esta em `src/main/java/com/projeto/projeto`:

- `domain/`: entidades e modelo de negocio por contexto (`album`, `artista`, `musica`, etc.).
- `application/`: casos de uso, DTOs, mappers e contratos.
  - `port/in`: contratos de entrada (use cases).
  - `port/out`: contratos de saida (gateways).
  - `usecase`: implementacoes dos casos de uso.
- `infrastructure/`: adaptadores.
  - `adapter/inbound/rest`: controllers HTTP.
  - `adapter/outbound/persistence`: repositorios JPA e gateways JPA.

## Estrutura resumida

```text
src/
  main/
    java/com/projeto/projeto/
      application/
      domain/
      infrastructure/
    resources/
      application.properties
  test/
    java/com/projeto/projeto/
```

## Pre-requisitos

- JDK 17 instalado
- PostgreSQL em execucao
- Banco `audio` criado

## Configuracao local

As configuracoes atuais estao em `src/main/resources/application.properties`.

Exemplo atual:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/audio
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

## Como executar

No Windows (PowerShell):

```powershell
Set-Location "C:\Users\tecnologia3\Documents\Chrys\Intellij\projeto"
.\mvnw.cmd spring-boot:run
```

A API sobe por padrao em:

- `http://localhost:8080`

## Como rodar testes

```powershell
Set-Location "C:\Users\tecnologia3\Documents\Chrys\Intellij\projeto"
.\mvnw.cmd test
```

## Endpoints principais

Base URL local: `http://localhost:8080`

### Albums

- `POST /albums`
- `GET /albums`
- `GET /albums/{id}`
- `PUT /albums/{id}`
- `DELETE /albums/{id}`

### Artistas

- `POST /artistas`
- `GET /artistas`
- `GET /artistas/{id}`
- `PUT /artistas/{id}`
- `DELETE /artistas/{id}`

### Estilos

- `POST /estilos`
- `GET /estilos`
- `GET /estilos/{id}`
- `PUT /estilos/{id}`
- `DELETE /estilos/{id}`

### Musicas

- `POST /musicas`
- `GET /musicas`
- `GET /musicas/{id}`
- `PUT /musicas/{id}`
- `DELETE /musicas/{id}`

### Produtoras

- `POST /produtoras`
- `GET /produtoras`
- `GET /produtoras/{id}`
- `PUT /produtoras/{id}`
- `DELETE /produtoras/{id}`

### Usuarios

- `POST /usuarios`
- `GET /usuarios`
- `GET /usuarios/{id}`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

### Favoritas

- `POST /favoritas`
- `GET /favoritas`
- `GET /favoritas/{id}`
- `GET /favoritas/usuario/{usuarioId}`
- `PUT /favoritas/{id}`
- `DELETE /favoritas/{id}`
- `DELETE /favoritas/usuario/{usuarioId}`
- `DELETE /favoritas/usuario/{usuarioId}/musica/{musicaId}`

## Colecao Postman e exemplos

- Colecao Postman: `projeto.postman_collection.json`
- Exemplos de JSON: `EXEMPLOS_JSON.md`

## Melhorias recomendadas

- padronizar excecoes de negocio com handler global (`@RestControllerAdvice`);
- separar configuracoes por ambiente (`dev`, `test`, `prod`);
- ampliar cobertura de testes para controllers, gateways e cenarios de erro;
- adicionar pipeline CI (build + test).