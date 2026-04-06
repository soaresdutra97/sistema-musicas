package com.projeto.projeto.domain.produtora.model;

import com.projeto.projeto.domain.album.model.Album;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name="produtora")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produtora {

    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="nome", nullable = false, length = 150)
    private String nome;

    @OneToMany(mappedBy = "produtora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albunsLancados;

}
