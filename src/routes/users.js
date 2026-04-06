const express = require('express');
const bcrypt = require('bcryptjs');
const { User } = require('../models');
const authMiddleware = require('../middleware/auth');

const router = express.Router();

// GET /api/users - list all users (protected)
router.get('/', authMiddleware, async (req, res) => {
  try {
    const users = await User.findAll({
      attributes: ['id', 'name', 'email', 'createdAt', 'updatedAt'],
    });
    return res.json(users);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// GET /api/users/:id - get a user (protected)
router.get('/:id', authMiddleware, async (req, res) => {
  try {
    const user = await User.findByPk(req.params.id, {
      attributes: ['id', 'name', 'email', 'createdAt', 'updatedAt'],
    });
    if (!user) return res.status(404).json({ error: 'User not found' });
    return res.json(user);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// PUT /api/users/:id - update a user (protected, own account)
router.put('/:id', authMiddleware, async (req, res) => {
  try {
    if (req.userId !== parseInt(req.params.id, 10)) {
      return res.status(403).json({ error: 'Forbidden' });
    }
    const user = await User.findByPk(req.params.id);
    if (!user) return res.status(404).json({ error: 'User not found' });

    const { name, email, password } = req.body;
    if (name) user.name = name;
    if (email) user.email = email;
    if (password) user.password = await bcrypt.hash(password, 10);

    await user.save();
    return res.json({ id: user.id, name: user.name, email: user.email });
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

// DELETE /api/users/:id - delete a user (protected, own account)
router.delete('/:id', authMiddleware, async (req, res) => {
  try {
    if (req.userId !== parseInt(req.params.id, 10)) {
      return res.status(403).json({ error: 'Forbidden' });
    }
    const user = await User.findByPk(req.params.id);
    if (!user) return res.status(404).json({ error: 'User not found' });
    await user.destroy();
    return res.status(204).send();
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
});

module.exports = router;
