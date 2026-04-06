import request from 'supertest';
import { app } from '../src/app';
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

beforeEach(async () => {
  await prisma.favorita.deleteMany();
  await prisma.musica.deleteMany();
  await prisma.estilo.deleteMany();
});

afterAll(async () => {
  await prisma.$disconnect();
});

describe('Estilos', () => {
  it('deve listar estilos vazios', async () => {
    const res = await request(app).get('/estilos');
    expect(res.status).toBe(200);
    expect(res.body).toEqual([]);
  });

  it('deve criar um estilo', async () => {
    const res = await request(app).post('/estilos').send({ nome: 'Samba' });
    expect(res.status).toBe(201);
    expect(res.body.nome).toBe('Samba');
  });

  it('deve buscar estilo por id', async () => {
    const criado = await request(app).post('/estilos').send({ nome: 'Bossa Nova' });
    const res = await request(app).get(`/estilos/${criado.body.id}`);
    expect(res.status).toBe(200);
    expect(res.body.nome).toBe('Bossa Nova');
  });

  it('deve retornar 404 para estilo inexistente', async () => {
    const res = await request(app).get('/estilos/9999');
    expect(res.status).toBe(404);
  });

  it('deve atualizar um estilo', async () => {
    const criado = await request(app).post('/estilos').send({ nome: 'MPB' });
    const res = await request(app).put(`/estilos/${criado.body.id}`).send({ nome: 'Música Popular Brasileira' });
    expect(res.status).toBe(200);
    expect(res.body.nome).toBe('Música Popular Brasileira');
  });

  it('deve deletar um estilo', async () => {
    const criado = await request(app).post('/estilos').send({ nome: 'Forró' });
    const res = await request(app).delete(`/estilos/${criado.body.id}`);
    expect(res.status).toBe(204);
  });
});
