import { Request, Response, NextFunction } from 'express';
import { PrismaClient } from '@prisma/client';
import { z } from 'zod';

const prisma = new PrismaClient();

const musicaSchema = z.object({
  titulo: z.string().min(1),
  duracao: z.number().int().positive().optional(),
  artista_id: z.number().int().positive(),
  album_id: z.number().int().positive().optional(),
  estilo_id: z.number().int().positive().optional(),
});

export async function listarMusicas(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const musicas = await prisma.musica.findMany({ include: { artista: true, album: true, estilo: true } });
    res.json(musicas);
  } catch (err) {
    next(err);
  }
}

export async function buscarMusica(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const musica = await prisma.musica.findUnique({ where: { id: Number(id) }, include: { artista: true, album: true, estilo: true } });
    if (!musica) {
      res.status(404).json({ erro: 'Música não encontrada' });
      return;
    }
    res.json(musica);
  } catch (err) {
    next(err);
  }
}

export async function criarMusica(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const data = musicaSchema.parse(req.body);
    const musica = await prisma.musica.create({ data, include: { artista: true, album: true, estilo: true } });
    res.status(201).json(musica);
  } catch (err) {
    next(err);
  }
}

export async function atualizarMusica(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const data = musicaSchema.partial().parse(req.body);
    const musica = await prisma.musica.update({ where: { id: Number(id) }, data, include: { artista: true, album: true, estilo: true } });
    res.json(musica);
  } catch (err) {
    next(err);
  }
}

export async function deletarMusica(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    await prisma.musica.delete({ where: { id: Number(id) } });
    res.status(204).send();
  } catch (err) {
    next(err);
  }
}
