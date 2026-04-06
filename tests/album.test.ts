import request from 'supertest';
import { app } from '../src/app';
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

let artistaId: number;

beforeEach(async () => {
  await prisma.favorita.deleteMany();
  await prisma.musica.deleteMany();
  await prisma.album.deleteMany();
  await prisma.artista.deleteMany();
  const artista = await prisma.artista.create({ data: { nome: 'Test Artist' } });
  artistaId = artista.id;
});

afterAll(async () => {
  await prisma.$disconnect();
});

describe('Albuns', () => {
  it('deve listar albuns vazios', async () => {
    const res = await request(app).get('/albuns');
    expect(res.status).toBe(200);
    expect(res.body).toEqual([]);
  });

  it('deve criar um album', async () => {
    const res = await request(app).post('/albuns').send({ titulo: 'Construção', ano: 1971, artista_id: artistaId });
    expect(res.status).toBe(201);
    expect(res.body.titulo).toBe('Construção');
    expect(res.body.ano).toBe(1971);
  });

  it('deve buscar album por id', async () => {
    const criado = await request(app).post('/albuns').send({ titulo: 'Construção', artista_id: artistaId });
    const res = await request(app).get(`/albuns/${criado.body.id}`);
    expect(res.status).toBe(200);
    expect(res.body.titulo).toBe('Construção');
  });

  it('deve retornar 404 para album inexistente', async () => {
    const res = await request(app).get('/albuns/9999');
    expect(res.status).toBe(404);
  });

  it('deve atualizar um album', async () => {
    const criado = await request(app).post('/albuns').send({ titulo: 'Old Title', artista_id: artistaId });
    const res = await request(app).put(`/albuns/${criado.body.id}`).send({ titulo: 'New Title' });
    expect(res.status).toBe(200);
    expect(res.body.titulo).toBe('New Title');
  });

  it('deve deletar um album', async () => {
    const criado = await request(app).post('/albuns').send({ titulo: 'To Delete', artista_id: artistaId });
    const res = await request(app).delete(`/albuns/${criado.body.id}`);
    expect(res.status).toBe(204);
  });
});
