package org.pkmn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pkmn.entity.Move;
import org.pkmn.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/move", produces =  MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Tag(name="Move", description="API per la consultazione delle mosse")
public class MoveRest {

    @Autowired
    private MoveService moveService;

    @Operation(summary="Restituisce una mossa secondo l'id")
    @GetMapping("get-by-id")
    public Move getById(int id){
        return moveService.getById(id);
    }

    @Operation(summary="Restituisce una mossa secondo il nome della mossa")
    @GetMapping("get-by-name")
    public Move getByName(String name){
        return moveService.getByName(name);
    }
}
