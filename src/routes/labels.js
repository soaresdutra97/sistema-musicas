const express = require('express');
const { Label, Album } = require('../models');
const authMiddleware = require('../middleware/auth');

const router = express.Router();

// GET /api/labels
router.get('/', async (req, res) => {
  try {
    const labels = await Label.findAll();
    return res.json(labels);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// GET /api/labels/:id
router.get('/:id', async (req, res) => {
  try {
    const label = await Label.findByPk(req.params.id, {
      include: [{ model: Album, as: 'albums' }],
    });
    if (!label) return res.status(404).json({ error: 'Label not found' });
    return res.json(label);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// POST /api/labels (protected)
router.post('/', authMiddleware, async (req, res) => {
  try {
    const { name, country } = req.body;
    if (!name) return res.status(400).json({ error: 'Name is required' });
    const label = await Label.create({ name, country });
    return res.status(201).json(label);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// PUT /api/labels/:id (protected)
router.put('/:id', authMiddleware, async (req, res) => {
  try {
    const label = await Label.findByPk(req.params.id);
    if (!label) return res.status(404).json({ error: 'Label not found' });
    const { name, country } = req.body;
    if (name) label.name = name;
    if (country !== undefined) label.country = country;
    await label.save();
    return res.json(label);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// DELETE /api/labels/:id (protected)
router.delete('/:id', authMiddleware, async (req, res) => {
  try {
    const label = await Label.findByPk(req.params.id);
    if (!label) return res.status(404).json({ error: 'Label not found' });
    await label.destroy();
    return res.status(204).send();
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

module.exports = router;
