package org.pkmn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pkmn.entity.Type;
import org.pkmn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/type", produces =  MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Tag(name="Type", description="API per la consultazione dei tipi Pokémon")
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
