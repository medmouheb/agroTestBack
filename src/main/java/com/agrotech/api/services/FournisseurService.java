package com.agrotech.api.services;

import com.agrotech.api.model.Fournisseur;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.FournisseurDto;
import com.agrotech.api.exceptions.NotFoundException;

import java.util.List;


public interface FournisseurService extends BaseService<FournisseurDto, String> {
    void importCSV(List<CSVRecord> records);

    FournisseurDto findByCode(String code) throws NotFoundException;
    public void archive(String id) throws NotFoundException;

    public Page<FournisseurDto> findPage(int pageSize, int pageNumber, String filter);
Fournisseur findByname(String name)throws NotFoundException;
    public void setNotArchive(String id) throws NotFoundException;
    public Page<Fournisseur> findArchivedPage1(int pageSize, int pageNumber, String filter);

    public Page<FournisseurDto> findArchivedPage(int pageSize, int pageNumber, String filter);
    public Page<Fournisseur> findPage1(int pageSize, int pageNumber, String filter);
}
