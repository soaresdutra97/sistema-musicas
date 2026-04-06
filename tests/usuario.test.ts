import request from 'supertest';
import { app } from '../src/app';
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

beforeEach(async () => {
  await prisma.favorita.deleteMany();
  await prisma.usuario.deleteMany();
});

afterAll(async () => {
  await prisma.$disconnect();
});

describe('Usuarios', () => {
  it('deve registrar um usuario', async () => {
    const res = await request(app).post('/usuarios/registrar').send({
      nome: 'João Silva',
      email: 'joao@example.com',
      senha: 'senha123',
    });
    expect(res.status).toBe(201);
    expect(res.body.email).toBe('joao@example.com');
    expect(res.body.senha).toBeUndefined();
  });

  it('deve fazer login', async () => {
    await request(app).post('/usuarios/registrar').send({
      nome: 'Maria',
      email: 'maria@example.com',
      senha: 'senha123',
    });
    const res = await request(app).post('/usuarios/login').send({
      email: 'maria@example.com',
      senha: 'senha123',
    });
    expect(res.status).toBe(200);
    expect(res.body.token).toBeDefined();
  });

  it('deve rejeitar login com senha errada', async () => {
    await request(app).post('/usuarios/registrar').send({
      nome: 'Pedro',
      email: 'pedro@example.com',
      senha: 'senha123',
    });
    const res = await request(app).post('/usuarios/login').send({
      email: 'pedro@example.com',
      senha: 'senhaerrada',
    });
    expect(res.status).toBe(401);
  });

  it('deve listar usuarios sem senha', async () => {
    await request(app).post('/usuarios/registrar').send({ nome: 'Ana', email: 'ana@example.com', senha: 'senha123' });
    const res = await request(app).get('/usuarios');
    expect(res.status).toBe(200);
    expect(res.body[0].senha).toBeUndefined();
  });

  it('deve rejeitar email invalido no registro', async () => {
    const res = await request(app).post('/usuarios/registrar').send({ nome: 'Test', email: 'notanemail', senha: 'senha123' });
    expect(res.status).toBe(400);
  });

  it('deve rejeitar senha curta no registro', async () => {
    const res = await request(app).post('/usuarios/registrar').send({ nome: 'Test', email: 'test@example.com', senha: '123' });
    expect(res.status).toBe(400);
  });
});
