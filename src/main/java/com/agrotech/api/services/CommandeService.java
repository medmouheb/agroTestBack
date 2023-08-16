package com.agrotech.api.services;

import com.agrotech.api.dto.CommandeDto;
import com.agrotech.api.model.Commande;

import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;

import com.agrotech.api.exceptions.NotFoundException;

import java.util.List;

public interface CommandeService extends BaseService<CommandeDto, String> {
    void importCSV(List<CSVRecord> records);

    CommandeDto findByCode(String paymentTermCode) throws NotFoundException;
    public void archive(String id) throws NotFoundException;

    public Page<CommandeDto> findPage(int pageSize, int pageNumber, String filter);
    Commande findByname(String name)throws NotFoundException;
    public void setNotArchive(String id) throws NotFoundException;
    public Page<Commande> findArchivedPage1(int pageSize, int pageNumber, String filter);

    public Page<CommandeDto> findArchivedPage(int pageSize, int pageNumber, String filter);
    public Page<Commande> findPage1(int pageSize, int pageNumber, String filter);
}
