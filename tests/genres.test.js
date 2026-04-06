const request = require('supertest');
const { app, setupDatabase, teardownDatabase, registerAndLogin } = require('./helpers');

let token;

beforeAll(async () => {
  process.env.NODE_ENV = 'test';
  await setupDatabase();
  ({ token } = await registerAndLogin(null, { email: `genres_${Date.now()}@example.com` }));
});

afterAll(async () => {
  await teardownDatabase();
});

describe('Genres (Estilos)', () => {
  let genreId;

  test('GET /api/genres - should return empty array initially', async () => {
    const res = await request(app).get('/api/genres');
    expect(res.status).toBe(200);
    expect(Array.isArray(res.body)).toBe(true);
  });

  test('POST /api/genres - should create a genre', async () => {
    const res = await request(app)
      .post('/api/genres')
      .set('Authorization', `Bearer ${token}`)
      .send({ name: 'Rock', description: 'Classic rock music' });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty('id');
    expect(res.body.name).toBe('Rock');
    genreId = res.body.id;
  });

  test('POST /api/genres - should return 401 without token', async () => {
    const res = await request(app).post('/api/genres').send({ name: 'Jazz' });
    expect(res.status).toBe(401);
  });

  test('POST /api/genres - should return 400 without name', async () => {
    const res = await request(app)
      .post('/api/genres')
      .set('Authorization', `Bearer ${token}`)
      .send({ description: 'No name' });
    expect(res.status).toBe(400);
  });

  test('GET /api/genres/:id - should return genre with musics', async () => {
    const res = await request(app).get(`/api/genres/${genreId}`);
    expect(res.status).toBe(200);
    expect(res.body.id).toBe(genreId);
    expect(res.body).toHaveProperty('musics');
  });

  test('GET /api/genres/:id - should return 404 for unknown id', async () => {
    const res = await request(app).get('/api/genres/9999');
    expect(res.status).toBe(404);
  });

  test('PUT /api/genres/:id - should update genre', async () => {
    const res = await request(app)
      .put(`/api/genres/${genreId}`)
      .set('Authorization', `Bearer ${token}`)
      .send({ description: 'Updated description' });
    expect(res.status).toBe(200);
    expect(res.body.description).toBe('Updated description');
  });

  test('DELETE /api/genres/:id - should delete genre', async () => {
    const res = await request(app)
      .delete(`/api/genres/${genreId}`)
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(204);
  });

  test('GET /api/genres/:id - should return 404 after deletion', async () => {
    const res = await request(app).get(`/api/genres/${genreId}`);
    expect(res.status).toBe(404);
  });
});
