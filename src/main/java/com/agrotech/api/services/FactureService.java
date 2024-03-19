package com.agrotech.api.services;

import com.agrotech.api.dto.DevisDto;
import com.agrotech.api.dto.FactureDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Devis;
import com.agrotech.api.model.Facture;
import org.springframework.data.domain.Page;

public interface FactureService extends BaseService<FactureDto, String> {
    FactureDto findByCode(String code) throws NotFoundException;


    Page<Facture> getpages(int pageSize, int pageNumber, String filter, String farmername);
    Page<Facture> getpages1(int pageSize, int pageNumber, String filter );

    Page<Facture> getpagesarchive(int pageSize, int pageNumber, String filter);
    Page<Facture> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;


}

