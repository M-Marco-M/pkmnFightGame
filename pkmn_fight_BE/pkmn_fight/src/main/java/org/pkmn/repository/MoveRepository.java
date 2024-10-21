package org.pkmn.repository;

import org.pkmn.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Integer>{
}
