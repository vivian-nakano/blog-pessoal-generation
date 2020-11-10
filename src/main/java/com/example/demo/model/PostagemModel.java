package com.example.demo.model;

import java.util.Date;  

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // essa classe vai ser entidade do jpa hibernate
@Table(name = "postagem") // essa entidade dentro do banco de dados vai virar uma tabela
public class PostagemModel {

	@Id //
	@GeneratedValue(strategy = GenerationType.IDENTITY) // valor id será gerado, vai ser transformada em chave primária
	private long id;

	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;

	@NotNull
	@Size(min = 10, max = 500)
	private String texto;
	
	private int ano;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis()); // para calcular exatamente a data que ele
																		// postou! se um dado passou por essa classe,															// vai calcular
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	// agora dá pra conferir dentro do banco de dados, mySQL
	// tabela com nome postagem, com colunas de titulo, texto, date...
	// id autoincrement

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
