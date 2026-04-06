import { Request, Response, NextFunction } from 'express';
import { PrismaClient } from '@prisma/client';
import { z } from 'zod';

const prisma = new PrismaClient();

const albumSchema = z.object({
  titulo: z.string().min(1),
  ano: z.number().int().optional(),
  artista_id: z.number().int().positive(),
  produtora_id: z.number().int().positive().optional(),
});

export async function listarAlbuns(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const albuns = await prisma.album.findMany({ include: { artista: true, produtora: true } });
    res.json(albuns);
  } catch (err) {
    next(err);
  }
}

export async function buscarAlbum(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const album = await prisma.album.findUnique({ where: { id: Number(id) }, include: { artista: true, produtora: true } });
    if (!album) {
      res.status(404).json({ erro: 'Álbum não encontrado' });
      return;
    }
    res.json(album);
  } catch (err) {
    next(err);
  }
}

export async function criarAlbum(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const data = albumSchema.parse(req.body);
    const album = await prisma.album.create({ data, include: { artista: true, produtora: true } });
    res.status(201).json(album);
  } catch (err) {
    next(err);
  }
}

export async function atualizarAlbum(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const data = albumSchema.partial().parse(req.body);
    const album = await prisma.album.update({ where: { id: Number(id) }, data, include: { artista: true, produtora: true } });
    res.json(album);
  } catch (err) {
    next(err);
  }
}

export async function deletarAlbum(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    await prisma.album.delete({ where: { id: Number(id) } });
    res.status(204).send();
  } catch (err) {
    next(err);
  }
}
