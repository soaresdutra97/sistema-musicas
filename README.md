# sistema-musicas

Backend - API REST para gerenciamento de músicas, álbuns, artistas, estilos, produtoras, usuários e favoritas.

## Tecnologias

- Node.js + TypeScript
- Express.js
- Prisma ORM + SQLite
- JWT (autenticação)
- bcryptjs (hash de senhas)
- Zod (validação de entrada)
- Jest + Supertest (testes)

## Instalação e execução

```bash
# Instalar dependências
npm install

# Configurar variáveis de ambiente
cp .env.example .env

# Gerar cliente Prisma e aplicar migrações
npx prisma migrate dev --name init

# Iniciar servidor em modo desenvolvimento
npm run dev
```

## Endpoints

### Artistas
| Método | Rota           | Descrição             |
|--------|----------------|-----------------------|
| GET    | /artistas      | Listar todos          |
| GET    | /artistas/:id  | Buscar por ID         |
| POST   | /artistas      | Criar novo            |
| PUT    | /artistas/:id  | Atualizar             |
| DELETE | /artistas/:id  | Deletar               |

### Estilos
| Método | Rota          | Descrição             |
|--------|---------------|-----------------------|
| GET    | /estilos      | Listar todos          |
| GET    | /estilos/:id  | Buscar por ID         |
| POST   | /estilos      | Criar novo            |
| PUT    | /estilos/:id  | Atualizar             |
| DELETE | /estilos/:id  | Deletar               |

### Produtoras
| Método | Rota             | Descrição             |
|--------|------------------|-----------------------|
| GET    | /produtoras      | Listar todas          |
| GET    | /produtoras/:id  | Buscar por ID         |
| POST   | /produtoras      | Criar nova            |
| PUT    | /produtoras/:id  | Atualizar             |
| DELETE | /produtoras/:id  | Deletar               |

### Álbuns
| Método | Rota         | Descrição             |
|--------|--------------|-----------------------|
| GET    | /albuns      | Listar todos          |
| GET    | /albuns/:id  | Buscar por ID         |
| POST   | /albuns      | Criar novo            |
| PUT    | /albuns/:id  | Atualizar             |
| DELETE | /albuns/:id  | Deletar               |

### Músicas
| Método | Rota           | Descrição             |
|--------|----------------|-----------------------|
| GET    | /musicas       | Listar todas          |
| GET    | /musicas/:id   | Buscar por ID         |
| POST   | /musicas       | Criar nova            |
| PUT    | /musicas/:id   | Atualizar             |
| DELETE | /musicas/:id   | Deletar               |

### Usuários
| Método | Rota                   | Descrição             |
|--------|------------------------|-----------------------|
| POST   | /usuarios/registrar    | Registrar usuário     |
| POST   | /usuarios/login        | Login (retorna JWT)   |
| GET    | /usuarios              | Listar todos          |
| GET    | /usuarios/:id          | Buscar por ID         |
| DELETE | /usuarios/:id          | Deletar               |

### Favoritas *(requer autenticação JWT)*
| Método | Rota           | Descrição             |
|--------|----------------|-----------------------|
| GET    | /favoritas     | Listar favoritas      |
| POST   | /favoritas     | Adicionar favorita    |
| DELETE | /favoritas/:id | Remover favorita      |

## Autenticação

Para rotas protegidas, inclua o header:
```
Authorization: Bearer <token>
```

O token é obtido fazendo POST em `/usuarios/login` com email e senha.

## Testes

```bash
npm test
```
