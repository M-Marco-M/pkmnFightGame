package org.pkmn.repository;

import org.pkmn.entity.EsemplarePokedex;
import org.pkmn.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findById(int id);
}
