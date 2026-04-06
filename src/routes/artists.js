const express = require('express');
const { Artist, Album, Music } = require('../models');
const authMiddleware = require('../middleware/auth');

const router = express.Router();

// GET /api/artists
router.get('/', async (req, res) => {
  try {
    const artists = await Artist.findAll();
    return res.json(artists);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// GET /api/artists/:id
router.get('/:id', async (req, res) => {
  try {
    const artist = await Artist.findByPk(req.params.id, {
      include: [
        { model: Album, as: 'albums' },
        { model: Music, as: 'musics' },
      ],
    });
    if (!artist) return res.status(404).json({ error: 'Artist not found' });
    return res.json(artist);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// POST /api/artists (protected)
router.post('/', authMiddleware, async (req, res) => {
  try {
    const { name, bio } = req.body;
    if (!name) return res.status(400).json({ error: 'Name is required' });
    const artist = await Artist.create({ name, bio });
    return res.status(201).json(artist);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// PUT /api/artists/:id (protected)
router.put('/:id', authMiddleware, async (req, res) => {
  try {
    const artist = await Artist.findByPk(req.params.id);
    if (!artist) return res.status(404).json({ error: 'Artist not found' });
    const { name, bio } = req.body;
    if (name) artist.name = name;
    if (bio !== undefined) artist.bio = bio;
    await artist.save();
    return res.json(artist);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// DELETE /api/artists/:id (protected)
router.delete('/:id', authMiddleware, async (req, res) => {
  try {
    const artist = await Artist.findByPk(req.params.id);
    if (!artist) return res.status(404).json({ error: 'Artist not found' });
    await artist.destroy();
    return res.status(204).send();
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

module.exports = router;
