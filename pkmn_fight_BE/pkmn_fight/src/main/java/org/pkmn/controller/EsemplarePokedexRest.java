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
@Tag(name="Pokedex", description="API per la consultazione dei dati Pokedex")
public class EsemplarePokedexRest {

    @Autowired
    private EsemplarePokedexService esemplarePokedexService;

    @Operation(summary="Restituisce un Pokémon secondo il suo numero del pokédex")
    @GetMapping("get-by-id")
    public EsemplarePokedex getByPokedexNumber(@Parameter(description = "Numero del pokédex del Pokémon") @RequestParam int pokedex_number){
        return  esemplarePokedexService.getById(pokedex_number);
    }

    @Operation(summary = "Restituisce un Pokémon secondo il suo nome")
    @GetMapping("get-by-name")
    public EsemplarePokedex getByName(@Parameter(description = "Nome del pokémon") @RequestParam String name) {
        return esemplarePokedexService.getByName(name);
    }
}
