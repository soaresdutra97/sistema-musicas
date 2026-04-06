const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const Album = sequelize.define('Album', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true,
  },
  title: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  releaseYear: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  artistId: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
  labelId: {
    type: DataTypes.INTEGER,
    allowNull: true,
  },
});

module.exports = Album;
