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

import br.com.helpconnect.minhaLista.model.Categoria;
import br.com.helpconnect.minhaLista.repository.CategoriaRespository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRespository categoriaRespository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> finAllByCategorias() {
		
		return ResponseEntity.ok(categoriaRespository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findByIdLista(@PathVariable long id) {
		
		return categoriaRespository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/*@GetMapping("/{id}")
	public ResponseEntity<List<Categoria>> findAllByNomeProdutos(@PathVariable long id) {
		
		return ResponseEntity.ok(categoriaRespository.findAllByUsuarioContainingIgnoreCase(id));
	}*/
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRespository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria) {
		
		return ResponseEntity.ok(categoriaRespository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {
		categoriaRespository.deleteById(id);
	}
	
}
