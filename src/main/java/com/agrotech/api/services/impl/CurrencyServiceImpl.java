package com.agrotech.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.CurrencyRepository;
import com.agrotech.api.dto.CurrencyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CurrencyMapper;
import com.agrotech.api.model.Currency;
import com.agrotech.api.services.CurrencyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private final CurrencyRepository currencyRepository ;
	@Autowired
	private final CurrencyMapper currencyMapper ;


	public Currency saveCurrency(Currency entity) {
		return currencyRepository.save(entity);
	}


	@Override
	public CurrencyDto create(CurrencyDto dto) {
		return currencyMapper.toDto(saveCurrency(currencyMapper.toEntity(dto))) ;
	}

	@Override
	public CurrencyDto update(String id, CurrencyDto dto) throws NotFoundException {

		Optional<Currency> currencyOptional =  currencyRepository.findById(id);
		if(currencyOptional.isEmpty()) {
			throw new NotFoundException("Currency not found ");
		}

		Currency currencyExisting = currencyOptional.get();
		currencyMapper.partialUpdate(currencyExisting, dto);

	    return currencyMapper.toDto(saveCurrency(currencyExisting));
	}

	@Override
	public CurrencyDto findById(String id) throws NotFoundException {
		Optional<Currency> currencyOptional = currencyRepository.findById(id);
		if(currencyOptional.isEmpty()) {
			throw new NotFoundException("Currency not found ");
		}
		return currencyMapper.toDto(currencyOptional.get());
	}

	@Override
	public List<CurrencyDto> findAll() {
		return currencyRepository.findAll().stream()
				.map(currencyMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Page<Currency> findPage1(int pageSize, int pageNumber, String filter) {


		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Currency>  result =  currencyRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
		return result;
		// return new PageImpl<>(result);
	}

	@Override
	public Page<Currency> findArchivedPage1(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
		Page<Currency>  result =  currencyRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
		return result;
	}

	@Override
	public Currency findByname(String name) throws NotFoundException {
		return currencyRepository.findByName(name);
	}

	@Override
	public Page<CurrencyDto> findPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<CurrencyDto>  result = currencyRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
				.map(currencyMapper::toDto)
				.collect(Collectors.toList());
		return new PageImpl<>(result);
	}

	@Override
	public void delete(String id) throws NotFoundException {

		if(!currencyRepository.existsById(id)) {
			throw new NotFoundException("Currency not found ");
		}

		currencyRepository.deleteById(id);
	}

	@Override
	public void archive(String id) throws NotFoundException {
		Optional<Currency> groOptional =  currencyRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("Currency not found ");
		}
		Currency groExisting = groOptional.get();
		groExisting.setIsDeleted(true);
		currencyRepository.save(groExisting);

	}

	@Override
	public void setNotArchive(String id) throws NotFoundException {
		Optional<Currency> groOptional =  currencyRepository.findById(id);
		if(groOptional.isEmpty()) {
			throw new NotFoundException("CostCenter not found ");
		}
		Currency groExisting = groOptional.get();
		groExisting.setIsDeleted(false);
		currencyRepository.save(groExisting);

	}

	@Override
	public Page<CurrencyDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
		Pageable pageable = PageRequest.of(
				pageNumber,
				pageSize
		);
		List<CurrencyDto>  result = currencyRepository.findByNameContainingIgnoreCase(filter, pageable)
				.stream()
				.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
				.map(currencyMapper::toDto)
				.collect(Collectors.toList());

		return new PageImpl<>(result);
	}


	@Override
	public CurrencyDto findByCode(String code) throws NotFoundException {
		Optional<Currency> currencyOptional = currencyRepository.findByCode(code);
		if(currencyOptional.isEmpty()) {
			throw new NotFoundException("Currency not found ");
		}
		return currencyMapper.toDto(currencyOptional.get());
	}

}
