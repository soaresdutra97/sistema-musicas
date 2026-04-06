import request from 'supertest';
import { app } from '../src/app';
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

beforeEach(async () => {
  await prisma.favorita.deleteMany();
  await prisma.musica.deleteMany();
  await prisma.album.deleteMany();
  await prisma.artista.deleteMany();
});

afterAll(async () => {
  await prisma.$disconnect();
});

describe('Artistas', () => {
  it('deve listar artistas vazios', async () => {
    const res = await request(app).get('/artistas');
    expect(res.status).toBe(200);
    expect(res.body).toEqual([]);
  });

  it('deve criar um artista', async () => {
    const res = await request(app).post('/artistas').send({ nome: 'Chico Buarque', pais: 'Brasil' });
    expect(res.status).toBe(201);
    expect(res.body.nome).toBe('Chico Buarque');
    expect(res.body.pais).toBe('Brasil');
  });

  it('deve buscar artista por id', async () => {
    const criado = await request(app).post('/artistas').send({ nome: 'Caetano Veloso' });
    const res = await request(app).get(`/artistas/${criado.body.id}`);
    expect(res.status).toBe(200);
    expect(res.body.nome).toBe('Caetano Veloso');
  });

  it('deve retornar 404 para artista inexistente', async () => {
    const res = await request(app).get('/artistas/9999');
    expect(res.status).toBe(404);
  });

  it('deve atualizar um artista', async () => {
    const criado = await request(app).post('/artistas').send({ nome: 'Gilberto Gil' });
    const res = await request(app).put(`/artistas/${criado.body.id}`).send({ pais: 'Brasil' });
    expect(res.status).toBe(200);
    expect(res.body.pais).toBe('Brasil');
  });

  it('deve deletar um artista', async () => {
    const criado = await request(app).post('/artistas').send({ nome: 'Milton Nascimento' });
    const res = await request(app).delete(`/artistas/${criado.body.id}`);
    expect(res.status).toBe(204);
  });

  it('deve rejeitar artista sem nome', async () => {
    const res = await request(app).post('/artistas').send({ pais: 'Brasil' });
    expect(res.status).toBe(400);
  });
});
