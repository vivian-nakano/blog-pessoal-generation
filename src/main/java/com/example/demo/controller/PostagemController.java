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

import com.example.demo.model.PostagemModel;
import com.example.demo.repository.PostagemRepository;

@RestController //informa pro Spring que se trata de controlador para receber requisições
@RequestMapping("/postagens") //definir rota ou URL
@CrossOrigin("*") //sinal de vezes, essa classe aceita requisições de qualquer origem

public class PostagemController {
	
	@Autowired //SERVICO DE INJECAO DE DEPENDENCIA DO SPRING. Como é interface, nao dá pra instanciar. Instanciação fica por conta do spring: aí usa autowired
	private PostagemRepository repository; //repository é o nome
	
	@GetMapping //expor para nossa API que este metodo se trata de um get! sempre que vir requisicao externa, no caso requisicao get, dispara este método. 
	//isso é um método
	public ResponseEntity <List<PostagemModel>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostagemModel> GetById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) //devolve objeto do tipo postagem
				.orElse(ResponseEntity.notFound().build()); //not found caso nao exista ou caso tenha erro na requisicao
				//retorna por padrao optional, nulo ou populado
	}
	
	@GetMapping("/titulo/{titulo}") //responseentity 
	public ResponseEntity<List<PostagemModel>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<PostagemModel> post (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<PostagemModel> put (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	@GetMapping(value="/maior")
	public ResponseEntity<List<PostagemModel>> findAllMaior(){
	return ResponseEntity.ok(repository.findAllMaior());
	}
	
	@GetMapping(value="/ordem")
	public ResponseEntity<List<PostagemModel>> anosDesc(){
	return ResponseEntity.ok(repository.anosDesc());
	}
	
	@GetMapping(value="/asc")
	public ResponseEntity<List<PostagemModel>> anosAsc(){
	return ResponseEntity.ok(repository.anosAsc());
	}
	
	@GetMapping(value="/intervalo")
	public ResponseEntity<List<PostagemModel>> anosIntervalos(){
	return ResponseEntity.ok(repository.anosIntervalos());
	}
	
}

