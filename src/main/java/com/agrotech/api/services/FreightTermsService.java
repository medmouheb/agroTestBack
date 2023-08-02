package com.agrotech.api.services;

import com.agrotech.api.dto.FreightTermsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.FreightTerms;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FreightTermsService extends BaseService<FreightTermsDto, String> {

    FreightTermsDto findByfreighttermcode(String freighttermcode ) throws NotFoundException;

    Page<FreightTermsDto> findPage1(int pageSize, int pageNumber, String filter);

    Page<FreightTerms> getpages(int pageSize, int pageNumber, String filter);

    Page<FreightTerms> getpagesarchive(int pageSize, int pageNumber, String filter);

    public void archive(String id) throws NotFoundException;

    // Page<FreightTermsDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    FreightTerms findByfreighttermname(String freighttermname ) throws NotFoundException;

    public Page<FreightTermsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);

    public Page<FreightTermsDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}