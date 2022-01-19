package br.com.helpconnect.minhaLista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpconnect.minhaLista.model.Lista;
import br.com.helpconnect.minhaLista.repository.ListaRespository;

@RestController
@RequestMapping("/lista")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ListaController {
	
	@Autowired
	private ListaRespository reposiRespository;
	
	@GetMapping
	public ResponseEntity<List<Lista>> findAllByLista() {
		
		return ResponseEntity.ok(reposiRespository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lista> findByIdLista(@PathVariable long id) {
		
		return reposiRespository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Lista> postLista(@RequestBody Lista lista) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(reposiRespository.save(lista));
	}
	
	@PutMapping
	public ResponseEntity<Lista> putLista(@RequestBody Lista lista) {
		
		return ResponseEntity.ok(reposiRespository.save(lista));
	}
	
	@DeleteMapping("/{id}")
	public void deleteLista(@PathVariable long id) {
		reposiRespository.deleteById(id);
	}

}
