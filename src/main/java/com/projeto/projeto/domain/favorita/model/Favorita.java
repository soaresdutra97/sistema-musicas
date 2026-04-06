package com.projeto.projeto.domain.favorita.model;

import com.projeto.projeto.domain.musica.model.Musica;
import com.projeto.projeto.domain.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name="favorita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="musica_id")
    private Musica musica;

    @Column(name="data_favoritada")
    private LocalDate dataFavoritada;

}
