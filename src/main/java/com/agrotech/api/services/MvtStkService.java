package com.agrotech.api.services;

import com.agrotech.api.dto.FactureDto;
import com.agrotech.api.dto.MvtStkDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Facture;
import com.agrotech.api.model.MvtStk;
import org.springframework.data.domain.Page;

public interface MvtStkService extends BaseService<MvtStkDto, String> {
    MvtStkDto findByCode(String code) throws NotFoundException;


    Page<MvtStk> getpages(int pageSize, int pageNumber, String filter, String farmername);
    Page<MvtStk> getpages1(int pageSize, int pageNumber, String filter );

    Page<MvtStk> getpagesarchive(int pageSize, int pageNumber, String filter);
    Page<MvtStk> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;


}


