import express from 'express';
import { artistaRouter } from './routes/artistaRoutes';
import { estiloRouter } from './routes/estiloRoutes';
import { produtoraRouter } from './routes/produtoraRoutes';
import { albumRouter } from './routes/albumRoutes';
import { musicaRouter } from './routes/musicaRoutes';
import { usuarioRouter } from './routes/usuarioRoutes';
import { favoritaRouter } from './routes/favoritaRoutes';
import { errorHandler } from './middlewares/errorHandler';

const app = express();

app.use(express.json());

app.use('/artistas', artistaRouter);
app.use('/estilos', estiloRouter);
app.use('/produtoras', produtoraRouter);
app.use('/albuns', albumRouter);
app.use('/musicas', musicaRouter);
app.use('/usuarios', usuarioRouter);
app.use('/favoritas', favoritaRouter);

app.use(errorHandler);

export { app };
