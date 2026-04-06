import { Router } from 'express';
import { listarArtistas, buscarArtista, criarArtista, atualizarArtista, deletarArtista } from '../controllers/artistaController';

export const artistaRouter = Router();

artistaRouter.get('/', listarArtistas);
artistaRouter.get('/:id', buscarArtista);
artistaRouter.post('/', criarArtista);
artistaRouter.put('/:id', atualizarArtista);
artistaRouter.delete('/:id', deletarArtista);
