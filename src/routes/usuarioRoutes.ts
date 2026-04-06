import { Router } from 'express';
import { listarUsuarios, buscarUsuario, registrarUsuario, loginUsuario, deletarUsuario } from '../controllers/usuarioController';

export const usuarioRouter = Router();

usuarioRouter.post('/registrar', registrarUsuario);
usuarioRouter.post('/login', loginUsuario);
usuarioRouter.get('/', listarUsuarios);
usuarioRouter.get('/:id', buscarUsuario);
usuarioRouter.delete('/:id', deletarUsuario);
