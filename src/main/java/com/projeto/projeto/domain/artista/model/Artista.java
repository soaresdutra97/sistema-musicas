package com.projeto.projeto.domain.artista.model;

import com.projeto.projeto.domain.album.model.Album;
import com.projeto.projeto.domain.musica.model.Musica;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name="artista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false, length = 150)
    private String nome;

    //um artista tem muitas musicas
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Musica> musicasLancadas;

    //um artista tem muitos albuns
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albunsLancados;

}
