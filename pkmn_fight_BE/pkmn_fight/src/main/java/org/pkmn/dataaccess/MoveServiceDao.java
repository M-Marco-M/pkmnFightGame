package org.pkmn.dataaccess;

import org.pkmn.entity.Move;

public interface MoveServiceDao {
    Move getById(int id);
    Move getByName(String name);
}
