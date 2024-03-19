package com.agrotech.api.services;

import com.agrotech.api.dto.DeliveryDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Delivery;
import org.springframework.data.domain.Page;

public interface DeliveryService extends BaseService<DeliveryDto, String> {
    DeliveryDto findByCode(String code) throws NotFoundException;


    Page<Delivery> getpages(int pageSize, int pageNumber, String filter, String farmername);
    Page<Delivery> getpages1(int pageSize, int pageNumber, String filter );

    Page<Delivery> getpagesarchive(int pageSize, int pageNumber, String filter);
    Page<Delivery> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;

    Delivery findByname(String name) throws NotFoundException;

    public Page<DeliveryDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}
