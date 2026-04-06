package com.projeto.projeto.domain.estilo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="estilo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false, length = 150)
    private String nome;

}
