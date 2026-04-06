package com.projeto.projeto.domain.musica.model;

import com.projeto.projeto.domain.album.model.Album;
import com.projeto.projeto.domain.artista.model.Artista;
import com.projeto.projeto.domain.estilo.model.Estilo;
import com.projeto.projeto.domain.favorita.model.Favorita;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="musica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false, length = 150)
    private String nome;

    @Column(name="data_lancamento")
    private LocalDate dataLancamento;

    @Column(name="vezes_reproduzida", nullable = false, columnDefinition = "int default 0")
    private Integer vezesReproduzida;

    @Column(name="vezes_favoritada", nullable = false, columnDefinition = "int default 0")
    private Integer vezesFavoritada;

    @Column(name="numero_curtidas", nullable = false, columnDefinition = "int default 0")
    private Integer numeroCurtidas;

    @ManyToOne //muitas musicas para um album?
    @JoinColumn(name="album_id")
    private Album album;

    @ManyToOne //Muitas musicas para um artista?
    @JoinColumn(name="artista_id")
    private Artista artista;

    @ManyToOne //Muitas musicas para um estilo?
    @JoinColumn(name="estilo_id")
    private Estilo estilo;

    @OneToMany(mappedBy = "musica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorita> favoritadas;

    @PrePersist
    @PreUpdate
    private void garantirContadoresNaoNulos() {
        if (vezesReproduzida == null) {
            vezesReproduzida = 0;
        }
        if (vezesFavoritada == null) {
            vezesFavoritada = 0;
        }
        if (numeroCurtidas == null) {
            numeroCurtidas = 0;
        }
    }

}

