const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const Music = sequelize.define('Music', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true,
  },
  title: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  duration: {
    type: DataTypes.INTEGER,
    allowNull: true,
    comment: 'Duration in seconds',
  },
  albumId: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  artistId: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  genreId: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
});

module.exports = Music;
