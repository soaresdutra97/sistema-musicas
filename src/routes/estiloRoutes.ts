import { Router } from 'express';
import { listarEstilos, buscarEstilo, criarEstilo, atualizarEstilo, deletarEstilo } from '../controllers/estiloController';

export const estiloRouter = Router();

estiloRouter.get('/', listarEstilos);
estiloRouter.get('/:id', buscarEstilo);
estiloRouter.post('/', criarEstilo);
estiloRouter.put('/:id', atualizarEstilo);
estiloRouter.delete('/:id', deletarEstilo);
