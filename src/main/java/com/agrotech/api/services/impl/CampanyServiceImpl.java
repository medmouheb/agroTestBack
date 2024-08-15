package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CampanyMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.services.CampanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampanyServiceImpl implements CampanyService {

	@Autowired
	private final CampanyRepository campanyRepository;
	@Autowired
	private final CampanyMapper campanyMapper;
	@Autowired
	private final NotificationService notificationService;

	public Campany save(Campany dto) {
		return campanyRepository.save(dto);
	}

	@Override
	public CampanyDto create(CampanyDto dto) {
		CampanyDto createdDto = campanyMapper.toDto(save(campanyMapper.toEntity(dto)));
		notificationService.addNotification("Company created: " + createdDto.getName());
		return createdDto;
	}

	@Override
	public CampanyDto update(String id, CampanyDto dto) throws NotFoundException {
		Optional<Campany> camOptional = campanyRepository.findById(id);
		if (camOptional.isEmpty()) {
			throw new NotFoundException("Company not found");
		}

		Campany campanyExisting = camOptional.get();
		campanyMapper.partialUpdate(campanyExisting, dto);
		CampanyDto updatedDto = campanyMapper.toDto(save(campanyExisting));
		notificationService.addNotification("Company updated: " + updatedDto.getName());
		return updatedDto;
	}

	@Override
	public CampanyDto findById(String id) throws NotFoundException {
		Optional<Campany> campOptional = campanyRepository.findById(id);
		if (campOptional.isEmpty()) {
			throw new NotFoundException("Company not found");
		}
		return campanyMapper.toDto(campOptional.get());
	}

	@Override
	public List<CampanyDto> findAll() {
		return null;
	}

	@Override
	public List<CampanyDto> findAll(String farmer) throws NotFoundException {
		return campanyRepository.findByFarmer(farmer).stream()
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(String id) throws NotFoundException {
		if (!campanyRepository.existsById(id)) {
			throw new NotFoundException("Company not found");
		}
		campanyRepository.deleteById(id);
	}

	@Override
	public CampanyDto findByCode(String code, String farmer) throws NotFoundException {
		Optional<Campany> campOptional = campanyRepository.findByCodeAndFarmer(code, farmer);
		if (campOptional.isEmpty()) {
			throw new NotFoundException("Company not found");
		}
		return campanyMapper.toDto(campOptional.get());
	}

	@Override
	public Page<CampanyDto> findPage1(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		List<CampanyDto> result = campanyRepository.findByIsDeletedAndNameContainingIgnoreCase(false, filter, pageable)
				.stream()
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());
		return new PageImpl<>(result);
	}

	@Override
	public Page<Campany> getpages(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Campany> result = campanyRepository.findByIsDeletedAndNameContainingIgnoreCase(false, filter, pageable);
		return result;
	}

	@Override
	public Page<Campany> getpagesarchive(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return campanyRepository.findByIsDeletedAndNameContainingIgnoreCase(true, filter, pageable);
	}

	@Override
	public Page<Campany> getpages1(int pageSize, int pageNumber, String filter, String farmername) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return campanyRepository.findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(false, filter, farmername, pageable);
	}

	@Override
	public Page<Campany> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return campanyRepository.findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(true, filter, farmername, pageable);
	}

	@Override
	public Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<CampanyDto> result = campanyRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());
		return new PageImpl<>(result);
	}

	@Override
	public void archive(String id) throws NotFoundException {
		Optional<Campany> groOptional = campanyRepository.findById(id);
		if (groOptional.isEmpty()) {
			throw new NotFoundException("Company not found");
		}
		Campany groExisting = groOptional.get();
		groExisting.setIsDeleted(true);
		campanyRepository.save(groExisting);
	}

	@Override
	public void setNotArchive(String id) throws NotFoundException {
		Optional<Campany> groOptional = campanyRepository.findById(id);
		if (groOptional.isEmpty()) {
			throw new NotFoundException("Company not found");
		}
		Campany groExisting = groOptional.get();
		groExisting.setIsDeleted(false);
		campanyRepository.save(groExisting);
	}

	@Override
	public CampanyDto findByname(String farmer,String name)  throws NotFoundException {
		Optional<Campany> campOptional = campanyRepository.findByFarmerAndName(name, farmer);
		if (campOptional.isEmpty()) {
			throw new NotFoundException("Company not found");
		}
		return campanyMapper.toDto(campOptional.get());
	}

	@Override
	public Page<CampanyDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<CampanyDto> result = campanyRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g -> g.getIsDeleted() != null && g.getIsDeleted())
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());
		return new PageImpl<>(result);
	}

	@Override
	public List<Campany> findBynamee() throws NotFoundException {
		return campanyRepository.findAll();
	}

	@Override
	public Page<CampanyDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		List<CampanyDto> result = campanyRepository.findByIsDeletedAndNameContainingIgnoreCase(true, filter, pageable)
				.stream()
				.map(campanyMapper::toDto)
				.collect(Collectors.toList());
		return new PageImpl<>(result);
	}
}
