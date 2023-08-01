package com.agrotech.api.services;

import com.agrotech.api.model.CostCenter;
import com.agrotech.api.model.Currency;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.CategoryDto;
import com.agrotech.api.dto.CurrencyDto;
import com.agrotech.api.exceptions.NotFoundException;

public interface CurrencyService extends BaseService<CurrencyDto, String> {

	CurrencyDto findByCode(String code) throws NotFoundException;

	public void archive(String id) throws NotFoundException;

	public Page<CurrencyDto> findPage(int pageSize, int pageNumber, String filter);

	public void setNotArchive(String id) throws NotFoundException;

	public Page<CurrencyDto> findArchivedPage(int pageSize, int pageNumber, String filter);
	public Page<Currency> findPage1(int pageSize, int pageNumber, String filter);
	public Page<Currency> findArchivedPage1(int pageSize, int pageNumber, String filter);

	Currency findByname(String name)throws NotFoundException;





}
