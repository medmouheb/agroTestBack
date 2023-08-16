package com.agrotech.api.services;


import com.agrotech.api.dto.ChargeDto;
import com.agrotech.api.model.Charge;

import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;

import com.agrotech.api.exceptions.NotFoundException;

import java.util.List;
public interface ChargeService extends BaseService<ChargeDto, String> {
        void importCSV(List<CSVRecord> records);

    ChargeDto findByCode(String paymentTermCode) throws NotFoundException;
public void archive(String id) throws NotFoundException;

public Page<ChargeDto> findPage(int pageSize, int pageNumber, String filter);
        Charge findByname(String name)throws NotFoundException;
public void setNotArchive(String id) throws NotFoundException;
public Page<Charge> findArchivedPage1(int pageSize, int pageNumber, String filter);

public Page<ChargeDto> findArchivedPage(int pageSize, int pageNumber, String filter);
public Page<Charge> findPage1(int pageSize, int pageNumber, String filter);




        }
