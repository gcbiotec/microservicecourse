package br.com.fundatec.microservicecourse.controller;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import br.com.fundatec.microservicecourse.repository.CachorroRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cachorros")
public class CachorroController {

    @Autowired
    private CachorroRepository cachorroRepository;

    @GetMapping("{id}")
    public ResponseEntity<Cachorro> retornaCachorro(@PathVariable("id") Long id) {
        Optional<Cachorro> resultado = cachorroRepository.findById(id);

        if(resultado.isPresent()) {
            return new ResponseEntity<Cachorro>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Cachorro>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cachorro>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<Cachorro> resultado;

        //validar nome
        if (nome != null) {
            Optional<Cachorro> cachorro = cachorroRepository.findByNome(nome);

            if(cachorro.isPresent()) {
                resultado = Collections.singletonList(cachorro.get());
            } else {
                resultado = Collections.emptyList();
            }
        } else {
            resultado = cachorroRepository.findAll();
        }

        return new ResponseEntity(resultado, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity salvaCachorro(@RequestBody Cachorro cachorro) {
        cachorroRepository.save(cachorro);

        return ResponseEntity.ok().build();
    }

}
