package com.agrotech.api.services;

import com.agrotech.api.dto.DeliveryDto;
import com.agrotech.api.dto.DevisDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Delivery;
import com.agrotech.api.model.Devis;
import org.springframework.data.domain.Page;

public interface DevisService extends BaseService<DevisDto, String> {
    DevisDto findByCode(String code) throws NotFoundException;


        Page<Devis> getpages(int pageSize, int pageNumber, String filter, String farmername);
        Page<Devis> getpages1(int pageSize, int pageNumber, String filter );

        Page<Devis> getpagesarchive(int pageSize, int pageNumber, String filter);
        Page<Devis> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

        public void archive(String id) throws NotFoundException;


        public void setNotArchive(String id) throws NotFoundException;


    }
