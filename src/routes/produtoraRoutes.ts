import { Router } from 'express';
import { listarProdutoras, buscarProdutora, criarProdutora, atualizarProdutora, deletarProdutora } from '../controllers/produtoraController';

export const produtoraRouter = Router();

produtoraRouter.get('/', listarProdutoras);
produtoraRouter.get('/:id', buscarProdutora);
produtoraRouter.post('/', criarProdutora);
produtoraRouter.put('/:id', atualizarProdutora);
produtoraRouter.delete('/:id', deletarProdutora);
