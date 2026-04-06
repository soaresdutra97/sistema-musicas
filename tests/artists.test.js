const request = require('supertest');
const { app, setupDatabase, teardownDatabase, registerAndLogin } = require('./helpers');

let token;

beforeAll(async () => {
  process.env.NODE_ENV = 'test';
  await setupDatabase();
  ({ token } = await registerAndLogin(null, { email: `artists_${Date.now()}@example.com` }));
});

afterAll(async () => {
  await teardownDatabase();
});

describe('Artists', () => {
  let artistId;

  test('GET /api/artists - should return empty array initially', async () => {
    const res = await request(app).get('/api/artists');
    expect(res.status).toBe(200);
    expect(Array.isArray(res.body)).toBe(true);
  });

  test('POST /api/artists - should create an artist (authenticated)', async () => {
    const res = await request(app)
      .post('/api/artists')
      .set('Authorization', `Bearer ${token}`)
      .send({ name: 'The Beatles', bio: 'Legendary band' });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty('id');
    expect(res.body.name).toBe('The Beatles');
    artistId = res.body.id;
  });

  test('POST /api/artists - should return 401 without token', async () => {
    const res = await request(app).post('/api/artists').send({ name: 'No Auth' });
    expect(res.status).toBe(401);
  });

  test('POST /api/artists - should return 400 without name', async () => {
    const res = await request(app)
      .post('/api/artists')
      .set('Authorization', `Bearer ${token}`)
      .send({ bio: 'No name' });
    expect(res.status).toBe(400);
  });

  test('GET /api/artists/:id - should return artist with details', async () => {
    const res = await request(app).get(`/api/artists/${artistId}`);
    expect(res.status).toBe(200);
    expect(res.body.id).toBe(artistId);
    expect(res.body).toHaveProperty('albums');
    expect(res.body).toHaveProperty('musics');
  });

  test('GET /api/artists/:id - should return 404 for unknown id', async () => {
    const res = await request(app).get('/api/artists/9999');
    expect(res.status).toBe(404);
  });

  test('PUT /api/artists/:id - should update artist', async () => {
    const res = await request(app)
      .put(`/api/artists/${artistId}`)
      .set('Authorization', `Bearer ${token}`)
      .send({ bio: 'Updated bio' });
    expect(res.status).toBe(200);
    expect(res.body.bio).toBe('Updated bio');
  });

  test('DELETE /api/artists/:id - should delete artist', async () => {
    const res = await request(app)
      .delete(`/api/artists/${artistId}`)
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(204);
  });

  test('GET /api/artists/:id - should return 404 after deletion', async () => {
    const res = await request(app).get(`/api/artists/${artistId}`);
    expect(res.status).toBe(404);
  });
});
