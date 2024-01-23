package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.CropDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Crop;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CropService extends BaseService<CropDTO, String> {

    CropDTO findByCode(String code) throws NotFoundException;

    Page<CropDTO> findPage1(int pageSize, int pageNumber, String filter);

    Page<Crop> getpages(int pageSize, int pageNumber, String filter);

    Page<Crop> getpagesarchive(int pageSize, int pageNumber, String filter);

    public void archive(String id) throws NotFoundException;

    // Page<CropDTO> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    Crop findByname(String name) throws NotFoundException;

    public Page<CropDTO> findArchivedPage1(int pageSize, int pageNumber, String filter);

    public Page<CropDTO> findArchivedPage(int pageSize, int pageNumber, String filter);

    List<Crop> findBynamee() throws NotFoundException;

}
