import { Router } from 'express';
import { listarFavoritas, adicionarFavorita, removerFavorita } from '../controllers/favoritaController';
import { authMiddleware } from '../middlewares/auth';

export const favoritaRouter = Router();

favoritaRouter.use(authMiddleware);
favoritaRouter.get('/', listarFavoritas);
favoritaRouter.post('/', adicionarFavorita);
favoritaRouter.delete('/:id', removerFavorita);
