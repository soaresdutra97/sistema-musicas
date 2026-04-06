const express = require('express');
const { Favorite, Music, Artist, Genre, Album } = require('../models');
const authMiddleware = require('../middleware/auth');

const router = express.Router();

// GET /api/favorites - list authenticated user's favorites (protected)
router.get('/', authMiddleware, async (req, res) => {
  try {
    const favorites = await Favorite.findAll({
      where: { userId: req.userId },
      include: [
        {
          model: Music,
          as: 'music',
          include: [
            { model: Artist, as: 'artist' },
            { model: Genre, as: 'genre' },
            { model: Album, as: 'album' },
          ],
        },
      ],
    });
    return res.json(favorites);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// POST /api/favorites - add a music to favorites (protected)
router.post('/', authMiddleware, async (req, res) => {
  try {
    const { musicId } = req.body;
    if (!musicId) return res.status(400).json({ error: 'musicId is required' });

    const music = await Music.findByPk(musicId);
    if (!music) return res.status(404).json({ error: 'Music not found' });

    const existing = await Favorite.findOne({
      where: { userId: req.userId, musicId },
    });
    if (existing) return res.status(409).json({ error: 'Music already in favorites' });

    const favorite = await Favorite.create({ userId: req.userId, musicId });
    return res.status(201).json(favorite);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// DELETE /api/favorites/:id - remove a favorite (protected)
router.delete('/:id', authMiddleware, async (req, res) => {
  try {
    const favorite = await Favorite.findByPk(req.params.id);
    if (!favorite) return res.status(404).json({ error: 'Favorite not found' });
    if (favorite.userId !== req.userId) {
      return res.status(403).json({ error: 'Forbidden' });
    }
    await favorite.destroy();
    return res.status(204).send();
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

module.exports = router;
