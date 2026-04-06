const request = require('supertest');
const { app, setupDatabase, teardownDatabase, registerAndLogin } = require('./helpers');

let token;
let artistId;
let albumId;
let genreId;

beforeAll(async () => {
  process.env.NODE_ENV = 'test';
  await setupDatabase();
  ({ token } = await registerAndLogin(null, { email: `musics_${Date.now()}@example.com` }));

  const artistRes = await request(app)
    .post('/api/artists')
    .set('Authorization', `Bearer ${token}`)
    .send({ name: 'Artist For Music' });
  artistId = artistRes.body.id;

  const albumRes = await request(app)
    .post('/api/albums')
    .set('Authorization', `Bearer ${token}`)
    .send({ title: 'Album For Music', artistId });
  albumId = albumRes.body.id;

  const genreRes = await request(app)
    .post('/api/genres')
    .set('Authorization', `Bearer ${token}`)
    .send({ name: 'Pop' });
  genreId = genreRes.body.id;
});

afterAll(async () => {
  await teardownDatabase();
});

describe('Musics', () => {
  let musicId;

  test('GET /api/musics - should return empty array initially', async () => {
    const res = await request(app).get('/api/musics');
    expect(res.status).toBe(200);
    expect(Array.isArray(res.body)).toBe(true);
  });

  test('POST /api/musics - should create a music', async () => {
    const res = await request(app)
      .post('/api/musics')
      .set('Authorization', `Bearer ${token}`)
      .send({ title: 'Come Together', duration: 259, albumId, artistId, genreId });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty('id');
    expect(res.body.title).toBe('Come Together');
    musicId = res.body.id;
  });

  test('POST /api/musics - should return 401 without token', async () => {
    const res = await request(app).post('/api/musics').send({ title: 'No Auth' });
    expect(res.status).toBe(401);
  });

  test('POST /api/musics - should return 400 without title', async () => {
    const res = await request(app)
      .post('/api/musics')
      .set('Authorization', `Bearer ${token}`)
      .send({ duration: 180 });
    expect(res.status).toBe(400);
  });

  test('GET /api/musics/:id - should return music with relations', async () => {
    const res = await request(app).get(`/api/musics/${musicId}`);
    expect(res.status).toBe(200);
    expect(res.body.id).toBe(musicId);
    expect(res.body).toHaveProperty('album');
    expect(res.body).toHaveProperty('artist');
    expect(res.body).toHaveProperty('genre');
  });

  test('GET /api/musics/:id - should return 404 for unknown id', async () => {
    const res = await request(app).get('/api/musics/9999');
    expect(res.status).toBe(404);
  });

  test('PUT /api/musics/:id - should update music', async () => {
    const res = await request(app)
      .put(`/api/musics/${musicId}`)
      .set('Authorization', `Bearer ${token}`)
      .send({ title: 'Come Together (Remastered)', duration: 260 });
    expect(res.status).toBe(200);
    expect(res.body.title).toBe('Come Together (Remastered)');
    expect(res.body.duration).toBe(260);
  });

  test('DELETE /api/musics/:id - should delete music', async () => {
    const res = await request(app)
      .delete(`/api/musics/${musicId}`)
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(204);
  });

  test('GET /api/musics/:id - should return 404 after deletion', async () => {
    const res = await request(app).get(`/api/musics/${musicId}`);
    expect(res.status).toBe(404);
  });
});
