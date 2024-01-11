package com.agrotech.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.CategoryRepository;
import com.agrotech.api.dto.CategoryDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CategoryMapper;
import com.agrotech.api.model.Category;
import com.agrotech.api.services.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository ;
	@Autowired
	private CategoryMapper categoryMapper ;



	public Category save(Category entity) {
		return categoryRepository.save(entity);
	}



	@Override
	public CategoryDto create(CategoryDto dto) {
		return categoryMapper.toDto(save(categoryMapper.toEntity(dto)));
	}

	@Override
	public CategoryDto update(String id, CategoryDto dto) throws NotFoundException {
		Optional<Category> catgOptional =  categoryRepository.findById(id);
		if(catgOptional.isEmpty()) {
			throw new NotFoundException("Category not found ");
		}

		Category catg = catgOptional.get();
		categoryMapper.partialUpdate(catg, dto);

	return categoryMapper.toDto(save(catg));
	}



	@Override
	public CategoryDto findById(String id) throws NotFoundException {
		Optional<Category> campOptional = categoryRepository.findById(id);
		if(campOptional.isEmpty()) {
			throw new NotFoundException("Category not found ");
		}
		return categoryMapper.toDto(campOptional.get());
	}





	@Override
	public List<CategoryDto> findAll() {
		return categoryRepository.findAll().stream()
				.map(categoryMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Page<CategoryDto> findPage(int pageSize, int pageNumber, String filter) {
		return null;
	}

	@Override
	public void delete(String id) throws NotFoundException {
		if(!categoryRepository.existsById(id)) {
			throw new NotFoundException("Category not found ");
		}

		categoryRepository.deleteById(id);
	}




	@Override
	public CategoryDto findByCode(String code) throws NotFoundException {
		Optional<Category> campOptional = categoryRepository.findByCode(code);
		if(campOptional.isEmpty()) {
			throw new NotFoundException("Category not found ");
		}
		return categoryMapper.toDto(campOptional.get());
	}

}
