const express = require('express');
const { Music, Album, Artist, Genre } = require('../models');
const authMiddleware = require('../middleware/auth');

const router = express.Router();

// GET /api/musics
router.get('/', async (req, res) => {
  try {
    const musics = await Music.findAll({
      include: [
        { model: Album, as: 'album' },
        { model: Artist, as: 'artist' },
        { model: Genre, as: 'genre' },
      ],
    });
    return res.json(musics);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// GET /api/musics/:id
router.get('/:id', async (req, res) => {
  try {
    const music = await Music.findByPk(req.params.id, {
      include: [
        { model: Album, as: 'album' },
        { model: Artist, as: 'artist' },
        { model: Genre, as: 'genre' },
      ],
    });
    if (!music) return res.status(404).json({ error: 'Music not found' });
    return res.json(music);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// POST /api/musics (protected)
router.post('/', authMiddleware, async (req, res) => {
  try {
    const { title, duration, albumId, artistId, genreId } = req.body;
    if (!title) return res.status(400).json({ error: 'Title is required' });
    const music = await Music.create({ title, duration, albumId, artistId, genreId });
    return res.status(201).json(music);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// PUT /api/musics/:id (protected)
router.put('/:id', authMiddleware, async (req, res) => {
  try {
    const music = await Music.findByPk(req.params.id);
    if (!music) return res.status(404).json({ error: 'Music not found' });
    const { title, duration, albumId, artistId, genreId } = req.body;
    if (title) music.title = title;
    if (duration !== undefined) music.duration = duration;
    if (albumId !== undefined) music.albumId = albumId;
    if (artistId !== undefined) music.artistId = artistId;
    if (genreId !== undefined) music.genreId = genreId;
    await music.save();
    return res.json(music);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// DELETE /api/musics/:id (protected)
router.delete('/:id', authMiddleware, async (req, res) => {
  try {
    const music = await Music.findByPk(req.params.id);
    if (!music) return res.status(404).json({ error: 'Music not found' });
    await music.destroy();
    return res.status(204).send();
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

module.exports = router;
