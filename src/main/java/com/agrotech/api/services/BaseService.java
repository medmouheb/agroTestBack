package com.agrotech.api.services;

import com.itextpdf.text.DocumentException;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;

import com.agrotech.api.exceptions.NotFoundException;

import java.io.FileNotFoundException;
import java.util.List;

public interface BaseService<T, ID> {

    T create(T dto) throws DocumentException, FileNotFoundException;
    T update(ID id, T dto) throws NotFoundException;
    T findById(ID id) throws NotFoundException;
    List<T> findAll();
    Page<T> findPage(int pageSize, int pageNumber, String filter);
    void delete(ID id) throws NotFoundException;
}
