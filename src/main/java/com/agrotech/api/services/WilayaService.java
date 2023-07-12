package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.CurrencyDto;
import com.agrotech.api.dto.FermeDto;
import com.agrotech.api.dto.WillayaDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.Willaya;
import org.springframework.data.domain.Page;

public interface WilayaService extends BaseService<WillayaDto, String>{
    WillayaDto findByCode(String code) throws NotFoundException;


    public void archive(String id) throws NotFoundException;

    public Page<WillayaDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<WillayaDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<Willaya> findPage1(int pageSize, int pageNumber, String filter);
    public Page<Willaya> findArchivedPage1(int pageSize, int pageNumber, String filter);

}
