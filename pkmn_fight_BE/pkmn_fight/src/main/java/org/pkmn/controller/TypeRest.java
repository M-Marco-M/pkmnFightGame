package org.pkmn.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.pkmn.entity.Type;
import org.pkmn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class TypeRest {

    @Autowired
    private TypeService typeService;

    @Operation(summary="Restituisce un tipo secondo l'id")
    @GetMapping("get-by-id")
    public Type getById(int id){
        return typeService.getById(id);
    };

    @Operation(summary="Restituisce un tipo secondo il nome del tipo")
    @GetMapping("get-by-name")
    public Type getByName(String name){
        return typeService.getByName(name);
    }
}
