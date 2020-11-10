package com.example.demo.controller;

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

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categoria")

public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
	return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}") 
	public ResponseEntity<List<Categoria>> GetByTitulo(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Categoria>post (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
	@DeleteMapping
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
