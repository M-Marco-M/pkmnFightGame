package org.pkmn.repository;

import org.pkmn.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository <Trainer, Integer> {
}
