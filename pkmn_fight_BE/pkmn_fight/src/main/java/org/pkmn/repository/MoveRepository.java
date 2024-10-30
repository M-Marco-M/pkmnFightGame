package org.pkmn.repository;

import org.pkmn.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<Move, Integer>{
    Move findById(int id);
    Move findByName(String name);
}
