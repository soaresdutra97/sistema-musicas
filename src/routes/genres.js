const express = require('express');
const { Genre, Music } = require('../models');
const authMiddleware = require('../middleware/auth');

const router = express.Router();

// GET /api/genres
router.get('/', async (req, res) => {
  try {
    const genres = await Genre.findAll();
    return res.json(genres);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// GET /api/genres/:id
router.get('/:id', async (req, res) => {
  try {
    const genre = await Genre.findByPk(req.params.id, {
      include: [{ model: Music, as: 'musics' }],
    });
    if (!genre) return res.status(404).json({ error: 'Genre not found' });
    return res.json(genre);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// POST /api/genres (protected)
router.post('/', authMiddleware, async (req, res) => {
  try {
    const { name, description } = req.body;
    if (!name) return res.status(400).json({ error: 'Name is required' });
    const genre = await Genre.create({ name, description });
    return res.status(201).json(genre);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// PUT /api/genres/:id (protected)
router.put('/:id', authMiddleware, async (req, res) => {
  try {
    const genre = await Genre.findByPk(req.params.id);
    if (!genre) return res.status(404).json({ error: 'Genre not found' });
    const { name, description } = req.body;
    if (name) genre.name = name;
    if (description !== undefined) genre.description = description;
    await genre.save();
    return res.json(genre);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// DELETE /api/genres/:id (protected)
router.delete('/:id', authMiddleware, async (req, res) => {
  try {
    const genre = await Genre.findByPk(req.params.id);
    if (!genre) return res.status(404).json({ error: 'Genre not found' });
    await genre.destroy();
    return res.status(204).send();
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

module.exports = router;
