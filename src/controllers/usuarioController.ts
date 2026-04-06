import { Request, Response, NextFunction } from 'express';
import { PrismaClient } from '@prisma/client';
import { z } from 'zod';
import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';

const prisma = new PrismaClient();

const registroSchema = z.object({
  nome: z.string().min(1),
  email: z.string().email(),
  senha: z.string().min(6),
});

const loginSchema = z.object({
  email: z.string().email(),
  senha: z.string(),
});

export async function listarUsuarios(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const usuarios = await prisma.usuario.findMany({ select: { id: true, nome: true, email: true } });
    res.json(usuarios);
  } catch (err) {
    next(err);
  }
}

export async function buscarUsuario(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    const usuario = await prisma.usuario.findUnique({
      where: { id: Number(id) },
      select: { id: true, nome: true, email: true },
    });
    if (!usuario) {
      res.status(404).json({ erro: 'Usuário não encontrado' });
      return;
    }
    res.json(usuario);
  } catch (err) {
    next(err);
  }
}

export async function registrarUsuario(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const data = registroSchema.parse(req.body);
    const senhaHash = await bcrypt.hash(data.senha, 10);
    const usuario = await prisma.usuario.create({
      data: { nome: data.nome, email: data.email, senha: senhaHash },
      select: { id: true, nome: true, email: true },
    });
    res.status(201).json(usuario);
  } catch (err) {
    next(err);
  }
}

export async function loginUsuario(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { email, senha } = loginSchema.parse(req.body);
    const usuario = await prisma.usuario.findUnique({ where: { email } });
    if (!usuario || !(await bcrypt.compare(senha, usuario.senha))) {
      res.status(401).json({ erro: 'Email ou senha inválidos' });
      return;
    }
    const secret = process.env.JWT_SECRET || 'secret';
    const token = jwt.sign({ id: usuario.id }, secret, { expiresIn: '7d' });
    res.json({ token });
  } catch (err) {
    next(err);
  }
}

export async function deletarUsuario(req: Request, res: Response, next: NextFunction): Promise<void> {
  try {
    const { id } = req.params;
    await prisma.usuario.delete({ where: { id: Number(id) } });
    res.status(204).send();
  } catch (err) {
    next(err);
  }
}
