package br.com.fundatec.microservicecourse.controller;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fundatec.microservicecourse.domain.Veterinario;
import br.com.fundatec.microservicecourse.repository.VeterinarioRepository;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@GetMapping("{id}")
	public ResponseEntity<Veterinario> retornaVeterinario(@PathVariable("id") Long id){
		Optional<Veterinario> resultado = veterinarioRepository.findById(id);
		
		if(resultado.isPresent()) {
            return new ResponseEntity<Veterinario>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Veterinario>(HttpStatus.NO_CONTENT);
        }
    }
	
	@GetMapping
    public ResponseEntity<List<Veterinario>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<Veterinario> resultado;

         if (nome != null) {
            Optional<Veterinario> veterinario = veterinarioRepository.findByNome(nome);

            if(veterinario.isPresent()) {
                resultado = Collections.singletonList(veterinario.get());
            } else {
                resultado = Collections.emptyList();
            }
            
        } else {
        	
            resultado = veterinarioRepository.findAll();
        }

        return new ResponseEntity(resultado, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity salvaVeterinario(@RequestBody Veterinario veterinario) {
        veterinarioRepository.save(veterinario);

        return ResponseEntity.ok().build();
    }

}