package com.agrotech.api.services;

import com.agrotech.api.model.Currency;
import com.agrotech.api.model.VendorSKU;
import com.agrotech.api.model.Warehouse;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.FournisseurDto;
import com.agrotech.api.dto.WarehouseDto;
import com.agrotech.api.exceptions.NotFoundException;

public interface WarehouseService extends BaseService<WarehouseDto, String> {

    WarehouseDto findByCode(String code) throws NotFoundException;
    public void archive(String id) throws NotFoundException;

    public Page<WarehouseDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<WarehouseDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<Warehouse> findPage1(int pageSize, int pageNumber, String filter);
    public Page<Warehouse> findArchivedPage1(int pageSize, int pageNumber, String filter);

}
