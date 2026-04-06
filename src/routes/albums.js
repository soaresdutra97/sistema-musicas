const express = require('express');
const { Album, Artist, Label, Music } = require('../models');
const authMiddleware = require('../middleware/auth');

const router = express.Router();

// GET /api/albums
router.get('/', async (req, res) => {
  try {
    const albums = await Album.findAll({
      include: [
        { model: Artist, as: 'artist' },
        { model: Label, as: 'label' },
      ],
    });
    return res.json(albums);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// GET /api/albums/:id
router.get('/:id', async (req, res) => {
  try {
    const album = await Album.findByPk(req.params.id, {
      include: [
        { model: Artist, as: 'artist' },
        { model: Label, as: 'label' },
        { model: Music, as: 'musics' },
      ],
    });
    if (!album) return res.status(404).json({ error: 'Album not found' });
    return res.json(album);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// POST /api/albums (protected)
router.post('/', authMiddleware, async (req, res) => {
  try {
    const { title, releaseYear, artistId, labelId } = req.body;
    if (!title) return res.status(400).json({ error: 'Title is required' });
    const album = await Album.create({ title, releaseYear, artistId, labelId });
    return res.status(201).json(album);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// PUT /api/albums/:id (protected)
router.put('/:id', authMiddleware, async (req, res) => {
  try {
    const album = await Album.findByPk(req.params.id);
    if (!album) return res.status(404).json({ error: 'Album not found' });
    const { title, releaseYear, artistId, labelId } = req.body;
    if (title) album.title = title;
    if (releaseYear !== undefined) album.releaseYear = releaseYear;
    if (artistId !== undefined) album.artistId = artistId;
    if (labelId !== undefined) album.labelId = labelId;
    await album.save();
    return res.json(album);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// DELETE /api/albums/:id (protected)
router.delete('/:id', authMiddleware, async (req, res) => {
  try {
    const album = await Album.findByPk(req.params.id);
    if (!album) return res.status(404).json({ error: 'Album not found' });
    await album.destroy();
    return res.status(204).send();
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

module.exports = router;
