package org.pkmn.service;

import org.pkmn.dataaccess.MoveServiceDao;
import org.pkmn.entity.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

    @Autowired
    MoveServiceDao moveServiceDao;

    public Move getById(int id) {
        return moveServiceDao.getById(id);
    }

    public Move getByName(String name) {
        return moveServiceDao.getByName(name);
    }
}
