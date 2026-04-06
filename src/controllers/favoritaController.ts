import { Response, NextFunction } from 'express';
import { PrismaClient } from '@prisma/client';
import { z } from 'zod';
import { AuthRequest } from '../middlewares/auth';

const prisma = new PrismaClient();

const favoritaSchema = z.object({
  musica_id: z.number().int().positive(),
});

export async function listarFavoritas(req: AuthRequest, res: Response, next: NextFunction): Promise<void> {
  try {
    const favoritas = await prisma.favorita.findMany({
      where: { usuario_id: req.usuarioId },
      include: { musica: true },
    });
    res.json(favoritas);
  } catch (err) {
    next(err);
  }
}

export async function adicionarFavorita(req: AuthRequest, res: Response, next: NextFunction): Promise<void> {
  try {
    const { musica_id } = favoritaSchema.parse(req.body);
    const favorita = await prisma.favorita.create({
      data: { usuario_id: req.usuarioId!, musica_id },
    });
    res.status(201).json(favorita);
  } catch (err) {
    next(err);
  }
}

export async function removerFavorita(req: AuthRequest, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    await prisma.favorita.delete({ where: { id: Number(id) } });
    res.status(204).send();
  } catch (err) {
    next(err);
  }
}
