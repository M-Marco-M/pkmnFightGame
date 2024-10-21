package org.pkmn.dataaccess;

import org.pkmn.entity.Type;

public interface TypeServiceDao {
    Type getById (int id);
    Type getByName (String name);
}
