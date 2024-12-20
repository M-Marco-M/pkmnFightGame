package org.pkmn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private int id;
    private String name;
}
