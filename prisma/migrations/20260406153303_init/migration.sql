-- CreateTable
CREATE TABLE "Artista" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "nome" TEXT NOT NULL,
    "pais" TEXT
);

-- CreateTable
CREATE TABLE "Estilo" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "nome" TEXT NOT NULL
);

-- CreateTable
CREATE TABLE "Produtora" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "nome" TEXT NOT NULL,
    "pais" TEXT
);

-- CreateTable
CREATE TABLE "Album" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "titulo" TEXT NOT NULL,
    "ano" INTEGER,
    "artista_id" INTEGER NOT NULL,
    "produtora_id" INTEGER,
    CONSTRAINT "Album_artista_id_fkey" FOREIGN KEY ("artista_id") REFERENCES "Artista" ("id") ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT "Album_produtora_id_fkey" FOREIGN KEY ("produtora_id") REFERENCES "Produtora" ("id") ON DELETE SET NULL ON UPDATE CASCADE
);

-- CreateTable
CREATE TABLE "Musica" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "titulo" TEXT NOT NULL,
    "duracao" INTEGER,
    "artista_id" INTEGER NOT NULL,
    "album_id" INTEGER,
    "estilo_id" INTEGER,
    CONSTRAINT "Musica_artista_id_fkey" FOREIGN KEY ("artista_id") REFERENCES "Artista" ("id") ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT "Musica_album_id_fkey" FOREIGN KEY ("album_id") REFERENCES "Album" ("id") ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT "Musica_estilo_id_fkey" FOREIGN KEY ("estilo_id") REFERENCES "Estilo" ("id") ON DELETE SET NULL ON UPDATE CASCADE
);

-- CreateTable
CREATE TABLE "Usuario" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "nome" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "senha" TEXT NOT NULL
);

-- CreateTable
CREATE TABLE "Favorita" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "usuario_id" INTEGER NOT NULL,
    "musica_id" INTEGER NOT NULL,
    CONSTRAINT "Favorita_usuario_id_fkey" FOREIGN KEY ("usuario_id") REFERENCES "Usuario" ("id") ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT "Favorita_musica_id_fkey" FOREIGN KEY ("musica_id") REFERENCES "Musica" ("id") ON DELETE RESTRICT ON UPDATE CASCADE
);

-- CreateIndex
CREATE UNIQUE INDEX "Estilo_nome_key" ON "Estilo"("nome");

-- CreateIndex
CREATE UNIQUE INDEX "Usuario_email_key" ON "Usuario"("email");

-- CreateIndex
CREATE UNIQUE INDEX "Favorita_usuario_id_musica_id_key" ON "Favorita"("usuario_id", "musica_id");
