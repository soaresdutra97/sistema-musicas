import { Request, Response, NextFunction } from 'express';
import { PrismaClient } from '@prisma/client';
import { z } from 'zod';

const prisma = new PrismaClient();

const artistaSchema = z.object({
  nome: z.string().min(1),
  pais: z.string().optional(),
});

export async function listarArtistas(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const artistas = await prisma.artista.findMany();
    res.json(artistas);
  } catch (err) {
    next(err);
  }
}

export async function buscarArtista(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const artista = await prisma.artista.findUnique({ where: { id: Number(id) } });
    if (!artista) {
      res.status(404).json({ erro: 'Artista não encontrado' });
      return;
    }
    res.json(artista);
  } catch (err) {
    next(err);
  }
}

export async function criarArtista(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const data = artistaSchema.parse(req.body);
    const artista = await prisma.artista.create({ data });
    res.status(201).json(artista);
  } catch (err) {
    next(err);
  }
}

export async function atualizarArtista(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const data = artistaSchema.partial().parse(req.body);
    const artista = await prisma.artista.update({ where: { id: Number(id) }, data });
    res.json(artista);
  } catch (err) {
    next(err);
  }
}

export async function deletarArtista(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    await prisma.artista.delete({ where: { id: Number(id) } });
    res.status(204).send();
  } catch (err) {
    next(err);
  }
}
