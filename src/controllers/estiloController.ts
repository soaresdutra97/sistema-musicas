import { Request, Response, NextFunction } from 'express';
import { PrismaClient } from '@prisma/client';
import { z } from 'zod';

const prisma = new PrismaClient();

const estiloSchema = z.object({
  nome: z.string().min(1),
});

export async function listarEstilos(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const estilos = await prisma.estilo.findMany();
    res.json(estilos);
  } catch (err) {
    next(err);
  }
}

export async function buscarEstilo(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const estilo = await prisma.estilo.findUnique({ where: { id: Number(id) } });
    if (!estilo) {
      res.status(404).json({ erro: 'Estilo não encontrado' });
      return;
    }
    res.json(estilo);
  } catch (err) {
    next(err);
  }
}

export async function criarEstilo(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const data = estiloSchema.parse(req.body);
    const estilo = await prisma.estilo.create({ data });
    res.status(201).json(estilo);
  } catch (err) {
    next(err);
  }
}

export async function atualizarEstilo(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const data = estiloSchema.partial().parse(req.body);
    const estilo = await prisma.estilo.update({ where: { id: Number(id) }, data });
    res.json(estilo);
  } catch (err) {
    next(err);
  }
}

export async function deletarEstilo(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    await prisma.estilo.delete({ where: { id: Number(id) } });
    res.status(204).send();
  } catch (err) {
    next(err);
  }
}
