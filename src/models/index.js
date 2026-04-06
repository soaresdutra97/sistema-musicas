const sequelize = require('../config/database');
const User = require('./User');
const Artist = require('./Artist');
const Genre = require('./Genre');
const Label = require('./Label');
const Album = require('./Album');
const Music = require('./Music');
const Favorite = require('./Favorite');

// Artist associations
Artist.hasMany(Album, { foreignKey: 'artistId', as: 'albums' });
Artist.hasMany(Music, { foreignKey: 'artistId', as: 'musics' });

// Label associations
Label.hasMany(Album, { foreignKey: 'labelId', as: 'albums' });

// Album associations
Album.belongsTo(Artist, { foreignKey: 'artistId', as: 'artist' });
Album.belongsTo(Label, { foreignKey: 'labelId', as: 'label' });
Album.hasMany(Music, { foreignKey: 'albumId', as: 'musics' });

// Genre associations
Genre.hasMany(Music, { foreignKey: 'genreId', as: 'musics' });

// Music associations
Music.belongsTo(Album, { foreignKey: 'albumId', as: 'album' });
Music.belongsTo(Artist, { foreignKey: 'artistId', as: 'artist' });
Music.belongsTo(Genre, { foreignKey: 'genreId', as: 'genre' });
Music.hasMany(Favorite, { foreignKey: 'musicId', as: 'favorites' });

// User associations
User.hasMany(Favorite, { foreignKey: 'userId', as: 'favorites' });

// Favorite associations
Favorite.belongsTo(User, { foreignKey: 'userId', as: 'user' });
Favorite.belongsTo(Music, { foreignKey: 'musicId', as: 'music' });

module.exports = {
  sequelize,
  User,
  Artist,
  Genre,
  Label,
  Album,
  Music,
  Favorite,
};
