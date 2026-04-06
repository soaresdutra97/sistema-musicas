import { Request, Response, NextFunction } from 'express';
import { PrismaClient } from '@prisma/client';
import { z } from 'zod';

const prisma = new PrismaClient();

const produtoraSchema = z.object({
  nome: z.string().min(1),
  pais: z.string().optional(),
});

export async function listarProdutoras(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const produtoras = await prisma.produtora.findMany();
    res.json(produtoras);
  } catch (err) {
    next(err);
  }
}

export async function buscarProdutora(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const produtora = await prisma.produtora.findUnique({ where: { id: Number(id) } });
    if (!produtora) {
      res.status(404).json({ erro: 'Produtora não encontrada' });
      return;
    }
    res.json(produtora);
  } catch (err) {
    next(err);
  }
}

export async function criarProdutora(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const data = produtoraSchema.parse(req.body);
    const produtora = await prisma.produtora.create({ data });
    res.status(201).json(produtora);
  } catch (err) {
    next(err);
  }
}

export async function atualizarProdutora(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const data = produtoraSchema.partial().parse(req.body);
    const produtora = await prisma.produtora.update({ where: { id: Number(id) }, data });
    res.json(produtora);
  } catch (err) {
    next(err);
  }
}

export async function deletarProdutora(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    await prisma.produtora.delete({ where: { id: Number(id) } });
    res.status(204).send();
  } catch (err) {
    next(err);
  }
}
