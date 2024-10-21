package org.pkmn.service;

import org.pkmn.dataaccess.impl.TypeServiceDaoImpl;
import org.pkmn.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    @Autowired
    private TypeServiceDaoImpl typeServiceDao;

    public Type getById(int id) {
        return typeServiceDao.getById(id);
    };

    public Type getByName (String name) {
        return typeServiceDao.getByName(name);
    };

}
