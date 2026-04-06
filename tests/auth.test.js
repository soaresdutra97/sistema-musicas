const request = require('supertest');
const { app, setupDatabase, teardownDatabase } = require('./helpers');

beforeAll(async () => {
  process.env.NODE_ENV = 'test';
  await setupDatabase();
});

afterAll(async () => {
  await teardownDatabase();
});

describe('Auth', () => {
  const email = `auth_test_${Date.now()}@example.com`;

  test('POST /api/auth/register - should create a new user', async () => {
    const res = await request(app).post('/api/auth/register').send({
      name: 'Auth User',
      email,
      password: 'secret123',
    });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty('id');
    expect(res.body.email).toBe(email);
    expect(res.body).not.toHaveProperty('password');
  });

  test('POST /api/auth/register - should return 409 on duplicate email', async () => {
    const res = await request(app).post('/api/auth/register').send({
      name: 'Auth User',
      email,
      password: 'secret123',
    });
    expect(res.status).toBe(409);
  });

  test('POST /api/auth/register - should return 400 when fields missing', async () => {
    const res = await request(app).post('/api/auth/register').send({ name: 'No Email' });
    expect(res.status).toBe(400);
  });

  test('POST /api/auth/login - should return a JWT token', async () => {
    const res = await request(app).post('/api/auth/login').send({
      email,
      password: 'secret123',
    });
    expect(res.status).toBe(200);
    expect(res.body).toHaveProperty('token');
  });

  test('POST /api/auth/login - should return 401 for wrong password', async () => {
    const res = await request(app).post('/api/auth/login').send({
      email,
      password: 'wrongpassword',
    });
    expect(res.status).toBe(401);
  });

  test('POST /api/auth/login - should return 401 for unknown email', async () => {
    const res = await request(app).post('/api/auth/login').send({
      email: 'nobody@example.com',
      password: 'secret123',
    });
    expect(res.status).toBe(401);
  });
});
