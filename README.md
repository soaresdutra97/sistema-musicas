# sistema-musicas

Backend - Sistema de Músicas

REST API for managing music, albums, artists, genres (estilos), labels (produtoras), users, and favorites.

## Tech Stack

- **Node.js** + **Express.js**
- **Sequelize ORM** + **SQLite**
- **JWT** authentication
- **bcryptjs** password hashing
- **Jest** + **supertest** for testing

## Getting Started

```bash
# Install dependencies
npm install

# Copy environment file
cp .env.example .env

# Start server (development)
npm run dev

# Start server (production)
npm start
```

Server runs on `http://localhost:3000` by default.

## Running Tests

```bash
npm test
```

## API Endpoints

### Authentication

| Method | Endpoint              | Description         | Auth |
|--------|-----------------------|---------------------|------|
| POST   | /api/auth/register    | Register a new user | No   |
| POST   | /api/auth/login       | Login and get token | No   |

### Users

| Method | Endpoint        | Description       | Auth     |
|--------|-----------------|-------------------|----------|
| GET    | /api/users      | List all users    | Required |
| GET    | /api/users/:id  | Get user by id    | Required |
| PUT    | /api/users/:id  | Update own user   | Required |
| DELETE | /api/users/:id  | Delete own user   | Required |

### Artists (Artistas)

| Method | Endpoint           | Description           | Auth     |
|--------|--------------------|-----------------------|----------|
| GET    | /api/artists       | List all artists      | No       |
| GET    | /api/artists/:id   | Get artist by id      | No       |
| POST   | /api/artists       | Create artist         | Required |
| PUT    | /api/artists/:id   | Update artist         | Required |
| DELETE | /api/artists/:id   | Delete artist         | Required |

### Genres / Styles (Estilos)

| Method | Endpoint          | Description          | Auth     |
|--------|-------------------|----------------------|----------|
| GET    | /api/genres       | List all genres      | No       |
| GET    | /api/genres/:id   | Get genre by id      | No       |
| POST   | /api/genres       | Create genre         | Required |
| PUT    | /api/genres/:id   | Update genre         | Required |
| DELETE | /api/genres/:id   | Delete genre         | Required |

### Labels (Produtoras)

| Method | Endpoint          | Description          | Auth     |
|--------|-------------------|----------------------|----------|
| GET    | /api/labels       | List all labels      | No       |
| GET    | /api/labels/:id   | Get label by id      | No       |
| POST   | /api/labels       | Create label         | Required |
| PUT    | /api/labels/:id   | Update label         | Required |
| DELETE | /api/labels/:id   | Delete label         | Required |

### Albums (Álbuns)

| Method | Endpoint          | Description          | Auth     |
|--------|-------------------|----------------------|----------|
| GET    | /api/albums       | List all albums      | No       |
| GET    | /api/albums/:id   | Get album by id      | No       |
| POST   | /api/albums       | Create album         | Required |
| PUT    | /api/albums/:id   | Update album         | Required |
| DELETE | /api/albums/:id   | Delete album         | Required |

### Music (Músicas)

| Method | Endpoint          | Description          | Auth     |
|--------|-------------------|----------------------|----------|
| GET    | /api/musics       | List all musics      | No       |
| GET    | /api/musics/:id   | Get music by id      | No       |
| POST   | /api/musics       | Create music         | Required |
| PUT    | /api/musics/:id   | Update music         | Required |
| DELETE | /api/musics/:id   | Delete music         | Required |

### Favorites (Favoritas)

| Method | Endpoint              | Description                 | Auth     |
|--------|-----------------------|-----------------------------|----------|
| GET    | /api/favorites        | List user's favorites       | Required |
| POST   | /api/favorites        | Add music to favorites      | Required |
| DELETE | /api/favorites/:id    | Remove from favorites       | Required |

## Authentication

Authenticated routes require a `Bearer` token in the `Authorization` header:

```
Authorization: Bearer <token>
```

Obtain a token by registering and logging in via `/api/auth/login`.

## Data Models

- **User**: id, name, email, password
- **Artist**: id, name, bio
- **Genre**: id, name, description
- **Label**: id, name, country
- **Album**: id, title, releaseYear, artistId, labelId
- **Music**: id, title, duration (seconds), albumId, artistId, genreId
- **Favorite**: id, userId, musicId
