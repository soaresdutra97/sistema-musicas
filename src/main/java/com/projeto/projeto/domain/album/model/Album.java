package com.projeto.projeto.domain.album.model;

import com.projeto.projeto.domain.artista.model.Artista;
import com.projeto.projeto.domain.musica.model.Musica;
import com.projeto.projeto.domain.produtora.model.Produtora;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="album")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false, length = 150)
    private String nome;

    @Column(name="data_lancamento")
    private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name="produtora_id")
    private Produtora produtora; //muitos albums para uma produtora

    @ManyToOne
    @JoinColumn(name="artista_id")
    private Artista artista; //muitos albums para um artista

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Musica> musicas; //1 album para muitas musicas

}
