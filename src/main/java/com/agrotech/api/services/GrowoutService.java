package com.agrotech.api.services;

import com.agrotech.api.model.Currency;
import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.model.Growout;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.GrowoutDto;
import com.agrotech.api.exceptions.NotFoundException;

public interface GrowoutService extends BaseService<GrowoutDto, String> {

    GrowoutDto findByCode(String code) throws NotFoundException;

//    public Page<GrowoutDto> findPage(int pageSize, int pageNumber, String filter);
    public void archive(String id) throws NotFoundException;
    public Page<Growout> findArchivedPage1(int pageSize, int pageNumber, String filter);
    Growout findByname(String name)throws NotFoundException;
    public void setNotArchive(String id) throws NotFoundException;

    public Page<GrowoutDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<Growout> findPage1(int pageSize, int pageNumber, String filter);
}
