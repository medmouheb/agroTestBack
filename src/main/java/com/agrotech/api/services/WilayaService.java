package com.agrotech.api.services;

import com.agrotech.api.dto.WillayaDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Willaya;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WilayaService extends BaseService<WillayaDto, String>{
    WillayaDto findByCode(String code, String getusername) throws NotFoundException;


    List<WillayaDto> findAllByfarmer(String farmer);

    public void archive(String id) throws NotFoundException;

    public Page<WillayaDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<WillayaDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<Willaya> findPage1(int pageSize, int pageNumber, String filter);
    public Page<Willaya> findPage1Farmer(String farmer,int pageSize, int pageNumber, String filter);
    public Page<Willaya> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<Willaya> findArchivedPage1Farmer(String farmer,int pageSize, int pageNumber, String filter);
Willaya findByname(String name)throws NotFoundException;
}
