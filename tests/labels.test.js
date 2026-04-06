const request = require('supertest');
const { app, setupDatabase, teardownDatabase, registerAndLogin } = require('./helpers');

let token;

beforeAll(async () => {
  process.env.NODE_ENV = 'test';
  await setupDatabase();
  ({ token } = await registerAndLogin(null, { email: `labels_${Date.now()}@example.com` }));
});

afterAll(async () => {
  await teardownDatabase();
});

describe('Labels (Produtoras)', () => {
  let labelId;

  test('GET /api/labels - should return empty array initially', async () => {
    const res = await request(app).get('/api/labels');
    expect(res.status).toBe(200);
    expect(Array.isArray(res.body)).toBe(true);
  });

  test('POST /api/labels - should create a label', async () => {
    const res = await request(app)
      .post('/api/labels')
      .set('Authorization', `Bearer ${token}`)
      .send({ name: 'Sony Music', country: 'USA' });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty('id');
    expect(res.body.name).toBe('Sony Music');
    labelId = res.body.id;
  });

  test('POST /api/labels - should return 401 without token', async () => {
    const res = await request(app).post('/api/labels').send({ name: 'EMI' });
    expect(res.status).toBe(401);
  });

  test('POST /api/labels - should return 400 without name', async () => {
    const res = await request(app)
      .post('/api/labels')
      .set('Authorization', `Bearer ${token}`)
      .send({ country: 'UK' });
    expect(res.status).toBe(400);
  });

  test('GET /api/labels/:id - should return label with albums', async () => {
    const res = await request(app).get(`/api/labels/${labelId}`);
    expect(res.status).toBe(200);
    expect(res.body.id).toBe(labelId);
    expect(res.body).toHaveProperty('albums');
  });

  test('GET /api/labels/:id - should return 404 for unknown id', async () => {
    const res = await request(app).get('/api/labels/9999');
    expect(res.status).toBe(404);
  });

  test('PUT /api/labels/:id - should update label', async () => {
    const res = await request(app)
      .put(`/api/labels/${labelId}`)
      .set('Authorization', `Bearer ${token}`)
      .send({ country: 'Japan' });
    expect(res.status).toBe(200);
    expect(res.body.country).toBe('Japan');
  });

  test('DELETE /api/labels/:id - should delete label', async () => {
    const res = await request(app)
      .delete(`/api/labels/${labelId}`)
      .set('Authorization', `Bearer ${token}`);
    expect(res.status).toBe(204);
  });

  test('GET /api/labels/:id - should return 404 after deletion', async () => {
    const res = await request(app).get(`/api/labels/${labelId}`);
    expect(res.status).toBe(404);
  });
});
