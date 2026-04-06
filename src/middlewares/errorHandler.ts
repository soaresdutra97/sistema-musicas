import { Request, Response, NextFunction } from 'express';
import { ZodError } from 'zod';

export function errorHandler(err: unknown, req: Request, res: Response, next: NextFunction): void {
  if (err instanceof ZodError) {
    res.status(400).json({ erro: 'Dados inválidos', detalhes: err.issues });
    return;
  }
  if (err instanceof Error) {
    res.status(500).json({ erro: err.message });
    return;
  }
  res.status(500).json({ erro: 'Erro interno do servidor' });
}
