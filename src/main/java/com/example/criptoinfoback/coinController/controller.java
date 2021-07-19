package com.example.criptoinfoback.coinController;

import com.example.criptoinfoback.interImpl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiprovider")
@CrossOrigin
public class controller {
    @Autowired
    ApiPokemon apiPokemon;
    @Autowired
    ApiMarket apiMarket;
    @Autowired
    ApiNationalize apiNationalize;
    @Autowired
    ApiAgify apiAgify;
    @Autowired
    ApiWebSearch apiWebSearch;
    @Autowired
    ApiFutbol apiFutbol;
    @Autowired
    ApiDeezer apiDeezer;

    private final static Logger logger = LoggerFactory.getLogger(controller.class);

    @GetMapping("/pokemonlist/")
    public ResponseEntity<String> getAllPokemons(){
        logger.info("pokemonlist");
        return new ResponseEntity<String>(apiPokemon.getAllPokemons(), HttpStatus.OK);
    }

    @GetMapping("/pokemon/{nombrepokemon}")
    public ResponseEntity<String> getPokemon(@PathVariable ("nombrepokemon") String nombrePokemon){
        logger.info("pokemon/{nombrepokemon}");
        return new ResponseEntity<String>(apiPokemon.getPokemon(nombrePokemon), HttpStatus.OK);
    }

    @GetMapping("/nationalize/{name}")
    public ResponseEntity<String> getCountries(@PathVariable ("name") String name){
        logger.info("/nationalize");
        return new ResponseEntity<String>(apiNationalize.getCountries(name),HttpStatus.OK);
    }

    @GetMapping("/agify/{name}")
    public ResponseEntity<String> getOld(@PathVariable ("name") String name){
        logger.info("/agify");
        return new ResponseEntity<String>(apiAgify.getOld(name),HttpStatus.OK);
    }

    @PostMapping("/websearch")
    public ResponseEntity<String> getResults(@RequestBody String[] palabras){
        logger.info("/websearch");
        return new ResponseEntity<String>(apiWebSearch.getResults(palabras),HttpStatus.OK);
    }

    @PostMapping("/futbol")
    public ResponseEntity<String> getFutbol(@RequestBody String[] parametros){
        logger.info("/futbol");
        return new ResponseEntity<String>(apiFutbol.getResults(parametros),HttpStatus.OK);
    }

    @GetMapping("/deezer/{artistname}")
    public ResponseEntity<String> getArtist(@PathVariable ("artistname") String artistName){
        logger.info("/deezer");
        return new ResponseEntity<String>(apiDeezer.getArtista(artistName),HttpStatus.OK);
    }

    @GetMapping("/deezer/tracks/{idartista}")
    public ResponseEntity<String> getTracks(@PathVariable ("idartista") String artistid){
        logger.info("/deezer/tracks");
        return new ResponseEntity<String>(apiDeezer.getTracks(artistid),HttpStatus.OK);
    }

    @GetMapping("/marketnews/{company}")
    public ResponseEntity<String> getCompanyNews(@PathVariable("company") String company){
        logger.info("/marketnews");
        return new ResponseEntity<String>(apiMarket.getNews(company),HttpStatus.OK);
    }

    @GetMapping("/marketinfo/{company}")
    public ResponseEntity<String> getCompanyInfo(@PathVariable("company") String company){
        logger.info("/marketinfo");
        return new ResponseEntity<String>(apiMarket.getCompanyInfo(company),HttpStatus.OK);
    }
}
