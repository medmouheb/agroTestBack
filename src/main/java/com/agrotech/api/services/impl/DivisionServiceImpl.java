package com.agrotech.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.DivisionRepository;
import com.agrotech.api.dto.CurrencyDto;
import com.agrotech.api.dto.DivisionDTO;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.DivisionMapper;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.Division;
import com.agrotech.api.services.DivisionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DivisionServiceImpl implements DivisionService {

	@Autowired
	private final DivisionRepository divisionRepository;
	@Autowired
	private final DivisionMapper divisionMapper;

	public Division saveDivision(Division entity) {
		return divisionRepository.save(entity);
	}

	public Division save(Division dto) {

		return divisionRepository.save(dto);

	}
	@Override
	public Page<Division> findPage1(int pageSize, int pageNumber, String filter) {


		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Division>  result =  divisionRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
		return result;
		// return new PageImpl<>(result);
	}

	@Override
	public Division findByname(String name) throws NotFoundException {
		return divisionRepository.findByName(name);
	}

	@Override
	public List<Division> findBynamee() throws NotFoundException {
		return divisionRepository.findAll();
	}

	@Override
	public List<Division> findByCompanyName() throws NotFoundException {
		return divisionRepository.findAll();
	}

	@Override
	public DivisionDTO create(DivisionDTO dto) {

//		return divisionMapper.toDto(save(divisionMapper.toEntity(dto)));
		return divisionMapper.toDto(save(divisionMapper.toEntity(dto)));
	}

	@Override
	public DivisionDTO update(String id, DivisionDTO dto) throws NotFoundException {
		Optional<Division> divOptional = divisionRepository.findById(id);
		if (divOptional.isEmpty()) {
			throw new NotFoundException("Division not found ");
		}

		Division division = divOptional.get();
		divisionMapper.partialUpdate(division, dto);

		return divisionMapper.toDto(save(division));
	}

	@Override
	public DivisionDTO findById(String id) throws NotFoundException {
		Optional<Division> divOptional = divisionRepository.findById(id);
		if(divOptional.isEmpty()) {
			throw new NotFoundException("Division not found ");
		}
		return divisionMapper.toDto(divOptional.get());
	}

	@Override
	public List<DivisionDTO> findAll() {
		return divisionRepository.findAll().stream()
				.map(divisionMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
    public Page<DivisionDTO> findPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<DivisionDTO>  result = divisionRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
				.map(divisionMapper::toDto)
				.collect(Collectors.toList());
		return new PageImpl<>(result);
    }

	@Override
	public void delete(String id) throws NotFoundException {

		if(!divisionRepository.existsById(id)) {
			throw new NotFoundException("Division not found ");
		}	
		divisionRepository.deleteById(id); 
	}

	@Override
	public DivisionDTO findByCode(String code) throws NotFoundException {
		Optional<Division> divisionOptional = divisionRepository.findByCode(code);
		if(divisionOptional.isEmpty()) {
			throw new NotFoundException("Division not found ");
		}
		return divisionMapper.toDto(divisionOptional.get());
	}

	@Override
	public void archive(String id) throws NotFoundException {
		Optional<Division> groOptional =  divisionRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Currency not found ");
		}
		Division groExisting = groOptional.get();
		groExisting.setIsDeleted(true);
		divisionRepository.save(groExisting);

	}

	@Override
	public void setNotArchive(String id) throws NotFoundException {
		Optional<Division> groOptional =  divisionRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("CostCenter not found ");
		}
		Division groExisting = groOptional.get();
		groExisting.setIsDeleted(false);
		divisionRepository.save(groExisting);

	}

	@Override
	public Page<DivisionDTO> findArchivedPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<DivisionDTO>  result = divisionRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
				.map(divisionMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}

	@Override
	public Page<Division> findArchivedPage1(int pageSize, int pageNumber, String filter) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Division>  result =  divisionRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
		return result;
	}


}
