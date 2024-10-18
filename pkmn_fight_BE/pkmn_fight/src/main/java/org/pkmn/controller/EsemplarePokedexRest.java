package org.pkmn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pkmn.entity.EsemplarePokedex;
import org.pkmn.service.EsemplarePokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/esemplarePokemon", produces =  MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Tag(name="Gestione corsi", description="API per la gestione dei corsi")
public class EsemplarePokedexRest {

    @Autowired
    EsemplarePokedexService esemplarePokedexService;

    @Operation(summary="Restituisce un Pokémon secondo il suo numero del pokédex")
    @GetMapping("get-by-id")
    @RequestMapping
    public EsemplarePokedex getByPokedexNumber(@Parameter(description = "Numero del pokédex del Pokémon") @RequestParam int pokedex_number){
        return  esemplarePokedexService.getById(pokedex_number);
    }

}
