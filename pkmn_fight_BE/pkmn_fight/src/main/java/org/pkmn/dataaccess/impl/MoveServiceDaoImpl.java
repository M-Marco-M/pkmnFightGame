package org.pkmn.dataaccess.impl;

import org.pkmn.dataaccess.MoveServiceDao;
import org.pkmn.entity.Move;
import org.pkmn.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoveServiceDaoImpl implements MoveServiceDao {

    @Autowired
    private MoveRepository moveRepository;

    @Override
    public Move getById(int id) {
        return moveRepository.findById(id);
    }

    @Override
    public Move getByName(String name) {
        return moveRepository.findByName(name);
    }
}
