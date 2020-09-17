package br.com.fundatec.microservicecourse.controller;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import br.com.fundatec.microservicecourse.domain.Veterinario;
import br.com.fundatec.microservicecourse.repository.CachorroRepository;
import br.com.fundatec.microservicecourse.repository.VeterinarioRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cachorros")
public class CachorroController {

    @Autowired
    private CachorroRepository cachorroRepository;
	private VeterinarioRepository veterinarioRepository;
	private VeterinarioRequest veterinarioRequest;


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
    
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity adicionaVeterinario
    (@PathVariable Long id, @RequestBody VeterinarioRequest veterinarioRequest) {
      Optional <Cachorro> cachorroFromDB = cachorroRepository.findById(id);
    	if (cachorroFromDB.isPresent()) {
    		
    		Cachorro cachorro = cachorroFromDB.get();

       Optional<Veterinario> veterinarioFromDB = veterinarioRepository.findById(veterinarioRequest.getId());

    	if (veterinarioFromDB.isPresent()) {
    			
    		Veterinario veterinario = veterinarioFromDB.get();
    		
			cachorro.setMeuVeterinario(veterinario);
			
			cachorroRepository.save(cachorro);

	    	return new ResponseEntity(HttpStatus.OK);
    		}
    	}
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }    
    
    @DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCachorroById(@PathVariable Long id) {
        Optional <Cachorro> cachorroFromDB = cachorroRepository.findById(id);
        
        if (cachorroFromDB.isPresent()) {
    		Cachorro cachorro = cachorroFromDB.get();

			cachorroRepository.delete(cachorro);
    		
            return new ResponseEntity(HttpStatus.OK);
         
        } else {
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }
    }
}
