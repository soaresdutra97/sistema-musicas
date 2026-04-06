const express = require('express');
const rateLimit = require('express-rate-limit');

const authRoutes = require('./routes/auth');
const usersRoutes = require('./routes/users');
const artistsRoutes = require('./routes/artists');
const genresRoutes = require('./routes/genres');
const labelsRoutes = require('./routes/labels');
const albumsRoutes = require('./routes/albums');
const musicsRoutes = require('./routes/musics');
const favoritesRoutes = require('./routes/favorites');

const app = express();

app.use(express.json());

const apiLimiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 100,
  standardHeaders: true,
  legacyHeaders: false,
  skip: () => process.env.NODE_ENV === 'test',
});

app.use('/api/', apiLimiter);

app.get('/', (req, res) => {
  res.json({ message: 'Sistema Músicas API', version: '1.0.0' });
});

app.use('/api/auth', authRoutes);
app.use('/api/users', usersRoutes);
app.use('/api/artists', artistsRoutes);
app.use('/api/genres', genresRoutes);
app.use('/api/labels', labelsRoutes);
app.use('/api/albums', albumsRoutes);
app.use('/api/musics', musicsRoutes);
app.use('/api/favorites', favoritesRoutes);

module.exports = app;
