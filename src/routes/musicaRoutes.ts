import { Router } from 'express';
import { listarMusicas, buscarMusica, criarMusica, atualizarMusica, deletarMusica } from '../controllers/musicaController';

export const musicaRouter = Router();

musicaRouter.get('/', listarMusicas);
musicaRouter.get('/:id', buscarMusica);
musicaRouter.post('/', criarMusica);
musicaRouter.put('/:id', atualizarMusica);
musicaRouter.delete('/:id', deletarMusica);
