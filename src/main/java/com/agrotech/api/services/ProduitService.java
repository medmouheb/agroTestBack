package com.agrotech.api.services;

import java.util.List;

import com.agrotech.api.model.Produit;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.NotFoundException;


public interface ProduitService extends BaseService<ProduitDto, String> {
	
    ProduitDto findByCode(String code) throws NotFoundException;

    void importCSV(List<CSVRecord> records);
    Produit findByname(String name)throws NotFoundException;
    public void archive(String id) throws NotFoundException;

    public Page<ProduitDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<ProduitDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<Produit> findPage1(int pageSize, int pageNumber, String filter);
    public Page<Produit> findArchivedPage1(int pageSize, int pageNumber, String filter);

}
