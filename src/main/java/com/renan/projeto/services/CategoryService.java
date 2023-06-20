package com.renan.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.renan.projeto.dtos.InsertCategoryDTO;
import com.renan.projeto.dtos.UpdateCategoryDTO;
import com.renan.projeto.entities.Category;
import com.renan.projeto.repositories.CategoryRepository;
import com.renan.projeto.services.exceptions.DatabaseException;
import com.renan.projeto.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;



@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Category findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Nenhuma categoria encontrada"));
		return entity;
	}
	
	@Transactional(readOnly = false)
	public Category insert(InsertCategoryDTO dto) {
		Category category = new Category();
		category.setNome(dto.getNome());
		category = categoryRepository.save(category);
		return category;
	}
	
	@Transactional(readOnly = false)
	public Category update(UpdateCategoryDTO dto) {
		try {
			Category category = categoryRepository.getReferenceById(dto.getId());
			category.setNome(dto.getNome());
			category = categoryRepository.save(category);
			return category;
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Nenhuma categoria encontrada");
		}		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
    	if (!categoryRepository.existsById(id)) {
    		throw new ResourceNotFoundException("Categoria não encontrada");
    	}
    	try {
            categoryRepository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
