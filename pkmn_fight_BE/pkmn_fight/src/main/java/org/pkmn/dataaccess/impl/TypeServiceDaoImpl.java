package org.pkmn.dataaccess.impl;

import org.pkmn.dataaccess.TypeServiceDao;
import org.pkmn.entity.Type;
import org.pkmn.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeServiceDaoImpl implements TypeServiceDao {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type getById(int id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type getByName(String name) {
        return typeRepository.findByName(name);
    }
}
