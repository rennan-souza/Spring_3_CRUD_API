package com.renan.projeto.dtos;

import jakarta.validation.constraints.NotNull;

public class UpdateCategoryDTO extends InsertCategoryDTO {

	@NotNull(message = "O id é obrigatório")
	private Long id;
	
	public UpdateCategoryDTO() {
		
	}
	
	public UpdateCategoryDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
