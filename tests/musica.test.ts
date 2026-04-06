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

describe('Musicas', () => {
  it('deve listar musicas vazias', async () => {
    const res = await request(app).get('/musicas');
    expect(res.status).toBe(200);
    expect(res.body).toEqual([]);
  });

  it('deve criar uma musica', async () => {
    const res = await request(app).post('/musicas').send({ titulo: 'Construção', duracao: 240, artista_id: artistaId });
    expect(res.status).toBe(201);
    expect(res.body.titulo).toBe('Construção');
    expect(res.body.duracao).toBe(240);
  });

  it('deve buscar musica por id', async () => {
    const criado = await request(app).post('/musicas').send({ titulo: 'Construção', artista_id: artistaId });
    const res = await request(app).get(`/musicas/${criado.body.id}`);
    expect(res.status).toBe(200);
    expect(res.body.titulo).toBe('Construção');
  });

  it('deve retornar 404 para musica inexistente', async () => {
    const res = await request(app).get('/musicas/9999');
    expect(res.status).toBe(404);
  });

  it('deve atualizar uma musica', async () => {
    const criado = await request(app).post('/musicas').send({ titulo: 'Old Title', artista_id: artistaId });
    const res = await request(app).put(`/musicas/${criado.body.id}`).send({ titulo: 'New Title' });
    expect(res.status).toBe(200);
    expect(res.body.titulo).toBe('New Title');
  });

  it('deve deletar uma musica', async () => {
    const criado = await request(app).post('/musicas').send({ titulo: 'To Delete', artista_id: artistaId });
    const res = await request(app).delete(`/musicas/${criado.body.id}`);
    expect(res.status).toBe(204);
  });
});
