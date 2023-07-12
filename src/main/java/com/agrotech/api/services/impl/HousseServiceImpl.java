package com.agrotech.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.HousseRepository;
import com.agrotech.api.dto.HousseDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.HousseMapper;
import com.agrotech.api.model.Housse;
import com.agrotech.api.services.HousseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HousseServiceImpl implements HousseService {
	@Autowired
	private HousseRepository housseRepository;
	@Autowired
	private HousseMapper housseMapper ;
	
	public Housse save(Housse entity) {
		return housseRepository.save(entity);
	}
	
	@Override
	public HousseDto create(HousseDto dto) {
		return housseMapper.toDto(save(housseMapper.toEntity(dto)));
	}
	
	
	@Override
	public HousseDto update(String id, HousseDto dto) throws NotFoundException {
		Optional<Housse> housseOptional =  housseRepository.findById(id);
		if(housseOptional.isEmpty()) {
			throw new NotFoundException("Housse not found ");
		}
		
		Housse housseExisting = housseOptional.get();
		housseMapper.partialUpdate(housseExisting, dto);
	
     	return housseMapper.toDto(save(housseExisting));
	}
	
	
	@Override
	public HousseDto findById(String id) throws NotFoundException {
		Optional<Housse> housseOptional = housseRepository.findById(id);
		if(housseOptional.isEmpty()) {
			throw new NotFoundException("Housse not found ");
		}
		return housseMapper.toDto(housseOptional.get());
	}
	
	
	@Override
	public List<HousseDto> findAll() {
		return housseRepository.findAll().stream()
				.map(housseMapper::toDto)
				.collect(Collectors.toList());
	
	}
	/*
	@Override
	public Page<HousseDto> findPage(int pageSize, int pageNumber, String filter) {
		    Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<FournisseurDto>  result = fournisseurRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
	}
	
	*/
	@Override
	public void delete(String id) throws NotFoundException {
		if(!housseRepository.existsById(id)) {
			throw new NotFoundException("Housse not found ");
		}
		
		housseRepository.deleteById(id); 
	}
	
	
	@Override
	public HousseDto findByCode(String code) throws NotFoundException {
		Optional<Housse> optional = housseRepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Housse not found");
        }
        return housseMapper.toDto(optional.get());
    }

	@Override
	public Page<HousseDto> findPage(int pageSize, int pageNumber, String filter) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
