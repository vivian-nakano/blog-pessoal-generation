package com.example.demo.repository;

import com.example.demo.model.PostagemModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository //avisar que a classe se trata de repositório, outra interface
//dentro da interface, tem varios contratos, findAll, findById, delete, save
//Jpa Hibernate já vem com algumas alterações, estas acima no caso
//por meio de método Query podemos criar/construir nossas consultas padronizadas, imagino que acrescentar
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {

	//consulta pelo título da postagem. FindAll busca todos pelo titulo. Titulo: nome do atributo da entidade
	public List <PostagemModel> findAllByTituloContainingIgnoreCase(String titulo); //containing: mesma coisa do like no sql, tudo que conter os caracteres dentro da variavel ele traz; ignore case: nao leva consideracao maiuscula e minuscula 
	
	@Query(value="select*from postagem where ano>2011",nativeQuery=true)
	List<PostagemModel> findAllMaior();
	
	@Query(value="select * from postagem ORDER BY ano DESC",nativeQuery=true)
	List<PostagemModel> anosDesc();
	
	@Query(value="select * from postagem where ano > 2011 order by ano asc\r\n",nativeQuery=true)
	List<PostagemModel> anosAsc();
	
	@Query(value="select*from postagem where ano >= 2011 and ano <= 2013",nativeQuery = true)
	List<PostagemModel> anosIntervalos();
}
