package com.projeto.projeto.domain.usuario.model;

import com.projeto.projeto.domain.favorita.model.Favorita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nome", nullable = false, length = 150)
    private String nome;
    
    @Column(name="email", nullable = false, length = 150)
    private String email;

    @Column(name="data_inscricao")
    private LocalDate dataInscricao;

    //Um usuario tem muitas musicas favoritas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorita> musicasFavoritas;
}
