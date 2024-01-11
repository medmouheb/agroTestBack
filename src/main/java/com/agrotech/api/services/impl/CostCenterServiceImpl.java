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

import org.springframework.data.domain.Sort;
import com.agrotech.api.Repository.CostCenterRepository;
import com.agrotech.api.dto.CostCenterDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CostCenterMapper;
import com.agrotech.api.model.CostCenter;
import com.agrotech.api.services.CostCenterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CostCenterServiceImpl implements CostCenterService {

	@Autowired
	private CostCenterRepository costCenterRepository ;

	@Autowired
	private CostCenterMapper costCenterMapper ;

	public CostCenter save(CostCenter entity) {
		return costCenterRepository.save(entity);
	}


	@Override
	public CostCenterDto create(CostCenterDto dto) {
		return costCenterMapper.toDto(save(costCenterMapper.toEntity(dto)));
	}

	@Override
	public CostCenterDto update(String id, CostCenterDto dto) throws NotFoundException {
		Optional<CostCenter> cosOptional = costCenterRepository.findById(id);
		if (cosOptional.isEmpty()) {
			throw new NotFoundException("Cost Center not found ");
		}

		CostCenter cosExisting = cosOptional.get();
		costCenterMapper.partialUpdate(cosExisting, dto);

		return costCenterMapper.toDto(save(cosExisting));
	}

	@Override
	public CostCenterDto findById(String id) throws NotFoundException {
		Optional<CostCenter> cosOptional = costCenterRepository.findById(id);
		if(cosOptional.isEmpty()) {
			throw new NotFoundException("Cost Center not found ");
		}
		return costCenterMapper.toDto(cosOptional.get());
	}

	@Override
	public List<CostCenterDto> findAll() {
		return costCenterRepository.findAll().stream()
				.map(costCenterMapper::toDto)
				.collect(Collectors.toList());
	}
	@Override
	public Page<CostCenter> findPage1(int pageSize, int pageNumber, String filter) {


		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<CostCenter>  result =  costCenterRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
		return result;
		// return new PageImpl<>(result);
	}

	@Override
	public Page<CostCenter> findArchivedPage1(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<CostCenter>  result =  costCenterRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
		return result;
	}

	@Override
	public Page<CostCenterDto> findPage(int pageSize, int pageNumber, String filter) {

		//Pageable pageable = PageRequest.of(
		//		pageNumber,
		//		pageSize
		//);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		List<CostCenterDto>  result = costCenterRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
			//	.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
				.map(costCenterMapper::toDto)
				.collect(Collectors.toList());
		return new PageImpl<>(result);
	}
	/*
	public Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter) {

		// Pageable pageable = PageRequest.of(
		// 		pageNumber,
		// 		pageSize
		// );
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		List<CampanyDto>  result = campanyRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				// .filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	*/



		@Override
	public void delete(String id) throws NotFoundException {
		if(!costCenterRepository.existsById(id)) {
			throw new NotFoundException("cost Center not found ");
		}

		 costCenterRepository.deleteById(id);
	}


	@Override
	public CostCenterDto findByCode(String code) throws NotFoundException {
		Optional<CostCenter> costOptional = costCenterRepository.findByCode(code);
		if(costOptional.isEmpty()) {
			throw new NotFoundException("cost center not found ");
		}
		return costCenterMapper.toDto(costOptional.get());
	}

	@Override
	public void archive(String id) throws NotFoundException {
		Optional<CostCenter> groOptional =  costCenterRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("cost center not found ");
		}
		CostCenter groExisting = groOptional.get();
		groExisting.setIsDeleted(true);
		costCenterRepository.save(groExisting);

	}

	@Override
	public void setNotArchive(String id) throws NotFoundException {
		Optional<CostCenter> groOptional =  costCenterRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("CostCenter not found ");
		}
		CostCenter groExisting = groOptional.get();
		groExisting.setIsDeleted(false);
		costCenterRepository.save(groExisting);

	}

	@Override
	public CostCenter findByname(String name) throws NotFoundException {
		return costCenterRepository.findByName(name);
	}

	@Override
	public Page<CostCenterDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<CostCenterDto>  result = costCenterRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
				.map(costCenterMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}

//	public Page<CampanyDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
//		Pageable pageable = PageRequest.of(
//				pageNumber,
//				pageSize
//		);
//		List<CampanyDto>  result = campanyRepository.findByNameContainingIgnoreCase(filter, pageable)
//				.stream()
//				.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
//				.map(campanyMapper::toDto)
//				.collect(Collectors.toList());
//
//		return new PageImpl<>(result);
//	}

}
