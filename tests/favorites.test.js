const request = require('supertest');
const { app, setupDatabase, teardownDatabase, registerAndLogin } = require('./helpers');

let token;
let userId;
let musicId;

beforeAll(async () => {
  process.env.NODE_ENV = 'test';
  await setupDatabase();
  ({ token, user } = await registerAndLogin(null, {
    name: 'Fav User',
    email: `favorites_${Date.now()}@example.com`,
  }));

  // Get our user id
  const usersRes = await request(app)
    .get('/api/users')
    .set('Authorization', `Bearer ${token}`);
  userId = usersRes.body[0].id;

  const artistRes = await request(app)
    .post('/api/artists')
    .set('Authorization', `Bearer ${token}`)
    .send({ name: 'Fav Artist' });

  const musicRes = await request(app)
    .post('/api/musics')
    .set('Authorization', `Bearer ${token}`)
    .send({ title: 'Fav Song', artistId: artistRes.body.id });
  musicId = musicRes.body.id;
});

afterAll(async () => {
  await teardownDatabase();
});

describe('Favorites', () => {
  let favoriteId;

  test('GET /api/favorites - should return empty array initially', async () => {
    const res = await request(app)
      .get('/api/favorites')
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(200);
    expect(Array.isArray(res.body)).toBe(true);
    expect(res.body.length).toBe(0);
  });

  test('GET /api/favorites - should return 401 without token', async () => {
    const res = await request(app).get('/api/favorites');
    expect(res.status).toBe(401);
  });

  test('POST /api/favorites - should add a music to favorites', async () => {
    const res = await request(app)
      .post('/api/favorites')
      .set('Authorization', `Bearer ${token}`)
      .send({ musicId });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty('id');
    expect(res.body.musicId).toBe(musicId);
    favoriteId = res.body.id;
  });

  test('POST /api/favorites - should return 409 for duplicate favorite', async () => {
    const res = await request(app)
      .post('/api/favorites')
      .set('Authorization', `Bearer ${token}`)
      .send({ musicId });
    expect(res.status).toBe(409);
  });

  test('POST /api/favorites - should return 400 without musicId', async () => {
    const res = await request(app)
      .post('/api/favorites')
      .set('Authorization', `Bearer ${token}`)
      .send({});
    expect(res.status).toBe(400);
  });

  test('POST /api/favorites - should return 404 for unknown musicId', async () => {
    const res = await request(app)
      .post('/api/favorites')
      .set('Authorization', `Bearer ${token}`)
      .send({ musicId: 9999 });
    expect(res.status).toBe(404);
  });

  test('GET /api/favorites - should list favorites after adding', async () => {
    const res = await request(app)
      .get('/api/favorites')
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(200);
    expect(res.body.length).toBe(1);
    expect(res.body[0]).toHaveProperty('music');
  });

  test('DELETE /api/favorites/:id - should remove a favorite', async () => {
    const res = await request(app)
      .delete(`/api/favorites/${favoriteId}`)
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(204);
  });

  test('GET /api/favorites - should be empty after deletion', async () => {
    const res = await request(app)
      .get('/api/favorites')
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(200);
    expect(res.body.length).toBe(0);
  });
});
