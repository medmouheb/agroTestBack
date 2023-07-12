package com.agrotech.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.Growoutrepository;
import com.agrotech.api.dto.GrowoutDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.GrowoutMapper;
import com.agrotech.api.model.Division;
import com.agrotech.api.model.Growout;
import com.agrotech.api.services.GrowoutService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GrowoutServiceImpl  implements GrowoutService{
	@Autowired
	private Growoutrepository growoutrepository ;
	@Autowired
	private GrowoutMapper growoutMapper ;
	
	
   public Growout save(Growout entity) {
	   return growoutrepository.save(entity);
   }
	
	@Override
	public GrowoutDto create(GrowoutDto dto) {
		return growoutMapper.toDto(save(growoutMapper.toEntity(dto)));
	}

	@Override
	public GrowoutDto update(String id, GrowoutDto dto) throws NotFoundException {
		Optional<Growout> groOptional =  growoutrepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Growout not found ");
		}
		
		Growout groExisting = groOptional.get();
		growoutMapper.partialUpdate(groExisting, dto);
	
	return growoutMapper.toDto(save(groExisting));
	}

	@Override
	public GrowoutDto findById(String id) throws NotFoundException {
		Optional<Growout> groOptional = growoutrepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Growout not found ");
		}
		return growoutMapper.toDto(groOptional.get());
	}

	@Override
	public Page<Growout> findPage1(int pageSize, int pageNumber, String filter) {


		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Growout>  result =  growoutrepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
		return result;
		// return new PageImpl<>(result);
	}

	@Override
	public List<GrowoutDto> findAll() {
		return growoutrepository.findAll().stream()
				.map(growoutMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(String id) throws NotFoundException {
		if(!growoutrepository.existsById(id)) {
			throw new NotFoundException("Growout not found ");
		}
		
		growoutrepository.deleteById(id); 
	}

	@Override
	public GrowoutDto findByCode(String code) throws NotFoundException {
		Optional<Growout> optional = growoutrepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Growout not found");
        }
        return growoutMapper.toDto(optional.get());
    }

	@Override
	public Page<GrowoutDto> findPage(int pageSize, int pageNumber, String filter) {

		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<GrowoutDto>  result = growoutrepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
				.map(growoutMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}

	@Override
	public void archive(String id) throws NotFoundException {
		Optional<Growout> groOptional =  growoutrepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Growout not found ");
		}
		Growout groExisting = groOptional.get();
		groExisting.setIsDeleted(true);
		growoutrepository.save(groExisting);

	}

	@Override
	public Page<Growout> findArchivedPage1(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Growout>  result =  growoutrepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
		return result;
	}

	@Override
	public void setNotArchive(String id) throws NotFoundException {
		Optional<Growout> groOptional =  growoutrepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Growout not found ");
		}
		Growout groExisting = groOptional.get();
		groExisting.setIsDeleted(false);
		growoutrepository.save(groExisting);

	}

	@Override
	public Page<GrowoutDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<GrowoutDto>  result = growoutrepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
				.map(growoutMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}

}
