const request = require('supertest');
const { app, setupDatabase, teardownDatabase, registerAndLogin } = require('./helpers');

let token;
let artistId;
let labelId;

beforeAll(async () => {
  process.env.NODE_ENV = 'test';
  await setupDatabase();
  ({ token } = await registerAndLogin(null, { email: `albums_${Date.now()}@example.com` }));

  const artistRes = await request(app)
    .post('/api/artists')
    .set('Authorization', `Bearer ${token}`)
    .send({ name: 'Artist For Album' });
  artistId = artistRes.body.id;

  const labelRes = await request(app)
    .post('/api/labels')
    .set('Authorization', `Bearer ${token}`)
    .send({ name: 'Label For Album' });
  labelId = labelRes.body.id;
});

afterAll(async () => {
  await teardownDatabase();
});

describe('Albums', () => {
  let albumId;

  test('GET /api/albums - should return empty array initially', async () => {
    const res = await request(app).get('/api/albums');
    expect(res.status).toBe(200);
    expect(Array.isArray(res.body)).toBe(true);
  });

  test('POST /api/albums - should create an album', async () => {
    const res = await request(app)
      .post('/api/albums')
      .set('Authorization', `Bearer ${token}`)
      .send({ title: 'Abbey Road', releaseYear: 1969, artistId, labelId });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty('id');
    expect(res.body.title).toBe('Abbey Road');
    albumId = res.body.id;
  });

  test('POST /api/albums - should return 401 without token', async () => {
    const res = await request(app).post('/api/albums').send({ title: 'No Auth' });
    expect(res.status).toBe(401);
  });

  test('POST /api/albums - should return 400 without title', async () => {
    const res = await request(app)
      .post('/api/albums')
      .set('Authorization', `Bearer ${token}`)
      .send({ releaseYear: 2000 });
    expect(res.status).toBe(400);
  });

  test('GET /api/albums/:id - should return album with artist and label', async () => {
    const res = await request(app).get(`/api/albums/${albumId}`);
    expect(res.status).toBe(200);
    expect(res.body.id).toBe(albumId);
    expect(res.body).toHaveProperty('artist');
    expect(res.body).toHaveProperty('label');
    expect(res.body).toHaveProperty('musics');
  });

  test('GET /api/albums/:id - should return 404 for unknown id', async () => {
    const res = await request(app).get('/api/albums/9999');
    expect(res.status).toBe(404);
  });

  test('PUT /api/albums/:id - should update album', async () => {
    const res = await request(app)
      .put(`/api/albums/${albumId}`)
      .set('Authorization', `Bearer ${token}`)
      .send({ title: 'Abbey Road (Remastered)' });
    expect(res.status).toBe(200);
    expect(res.body.title).toBe('Abbey Road (Remastered)');
  });

  test('DELETE /api/albums/:id - should delete album', async () => {
    const res = await request(app)
      .delete(`/api/albums/${albumId}`)
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(204);
  });

  test('GET /api/albums/:id - should return 404 after deletion', async () => {
    const res = await request(app).get(`/api/albums/${albumId}`);
    expect(res.status).toBe(404);
  });
});
