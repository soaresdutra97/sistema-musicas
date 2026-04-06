const { sequelize } = require('../src/models');
const app = require('../src/app');
const request = require('supertest');

async function setupDatabase() {
  process.env.NODE_ENV = 'test';
  await sequelize.sync({ force: true });
}

async function teardownDatabase() {
  await sequelize.close();
}

async function registerAndLogin(agent, userData = {}) {
  const user = {
    name: userData.name || 'Test User',
    email: userData.email || `test_${Date.now()}@example.com`,
    password: userData.password || 'password123',
  };
  await request(app).post('/api/auth/register').send(user);
  const res = await request(app).post('/api/auth/login').send({
    email: user.email,
    password: user.password,
  });
  return { token: res.body.token, user };
}

module.exports = { app, setupDatabase, teardownDatabase, registerAndLogin };
