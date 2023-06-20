package com.renan.projeto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InsertCategoryDTO {

	@NotBlank(message = "Campo requerido")
	@NotNull(message = "Campo obrigatório")
	private String nome;
	
	public InsertCategoryDTO() {
		
	}

	public InsertCategoryDTO(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
