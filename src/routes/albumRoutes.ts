import { Router } from 'express';
import { listarAlbuns, buscarAlbum, criarAlbum, atualizarAlbum, deletarAlbum } from '../controllers/albumController';

export const albumRouter = Router();

albumRouter.get('/', listarAlbuns);
albumRouter.get('/:id', buscarAlbum);
albumRouter.post('/', criarAlbum);
albumRouter.put('/:id', atualizarAlbum);
albumRouter.delete('/:id', deletarAlbum);
