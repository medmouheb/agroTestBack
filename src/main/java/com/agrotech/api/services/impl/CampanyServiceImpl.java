package com.agrotech.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.dto.CampanyDto;
import org.springframework.data.domain.Sort;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CampanyMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.services.CampanyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampanyServiceImpl implements CampanyService{
	
	@Autowired
	private CampanyRepository campanyRepository ;
	@Autowired
	private CampanyMapper campanyMapper ;

	
	
	public Campany save(Campany dto) {
		
		return campanyRepository.save(dto);
		
	}
	
	
	@Override
	public CampanyDto create(CampanyDto dto) {
		return campanyMapper.toDto(save(campanyMapper.toEntity(dto)));
		
	}

	@Override
	public CampanyDto update(String id, CampanyDto dto) throws NotFoundException {
		
		Optional<Campany> camOptional =  campanyRepository.findById(id);
		if(camOptional.isEmpty()) {
			throw new NotFoundException("Campany not found ");
		}
		
	Campany campanyExisting = camOptional.get();
	campanyMapper.partialUpdate(campanyExisting, dto);
	
	return campanyMapper.toDto(save(campanyExisting));
		
	}

	@Override
	public CampanyDto findById(String id) throws NotFoundException {
		Optional<Campany> campOptional = campanyRepository.findById(id);
		if(campOptional.isEmpty()) {
			throw new NotFoundException("Campany not found ");
		}
		return campanyMapper.toDto(campOptional.get());
	}

	@Override
	public List<CampanyDto> findAll() {
		return campanyRepository.findAll().stream()
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(String id) throws NotFoundException {

		if(!campanyRepository.existsById(id)) {
			throw new NotFoundException("Campany not found ");
		}
		
		campanyRepository.deleteById(id); 
		
		
	}


	@Override
	public CampanyDto findByCode(String code) throws NotFoundException {
		Optional<Campany> campOptional = campanyRepository.findByCode(code);
		if(campOptional.isEmpty()) {
			throw new NotFoundException("Campany not found ");
		}
		return campanyMapper.toDto(campOptional.get());
	}
	@Override
	public Page<CampanyDto> findPage1(int pageSize, int pageNumber, String filter) {


		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		List<CampanyDto> result =  campanyRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable)
				.stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
			.map(campanyMapper::toDto)
			.collect(Collectors.toList());
		//return result;
		return new PageImpl<>(result);
	}

	@Override
	public Page<Campany> getpages(int pageSize, int pageNumber, String filter) {


		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Campany> result =  campanyRepository.findByIsDeleted(false, pageable);

		return result;
	}

	@Override
	public Page<Campany> getpagesarchive(int pageSize, int pageNumber, String filter) {


		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Campany> result =  campanyRepository.findByIsDeleted(true, pageable);

		return result;
	}

	@Override
	public Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter) {

		// Pageable pageable = PageRequest.of(
		// 		pageNumber,
		// 		pageSize
		// );
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<CampanyDto>  result = campanyRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}

	@Override
	public void archive(String id) throws NotFoundException {
		Optional<Campany> groOptional =  campanyRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Campany not found ");
		}
		Campany groExisting = groOptional.get();
		groExisting.setIsDeleted(true);
		campanyRepository.save(groExisting);

	}

	@Override
	public void setNotArchive(String id) throws NotFoundException {
		Optional<Campany> groOptional =  campanyRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Campany not found ");
		}
		Campany groExisting = groOptional.get();
		groExisting.setIsDeleted(false);
		campanyRepository.save(groExisting);

	}

	@Override
	public Campany findByname(String name) throws NotFoundException {
		return campanyRepository.findByName(name);
	}

	@Override
	public Page<CampanyDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<CampanyDto>  result = campanyRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}
	@Override
	public Page<CampanyDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		List<CampanyDto>  result = campanyRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable)
				.stream()
				//.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}

//	@Override
//	public Page<CampanyDto> findPage1(int pageSize, int pageNumber, String filter) {
//
//
//		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
//		List<CampanyDto> result =  campanyRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable)
//				.stream()
//////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
//				.map(campanyMapper::toDto)
//				.collect(Collectors.toList());
//		//return result;
//		return new PageImpl<>(result);
//	}

	

}
