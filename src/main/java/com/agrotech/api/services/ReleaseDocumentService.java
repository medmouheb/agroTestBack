package com.agrotech.api.services;

import com.agrotech.api.dto.ReleaseDocumentDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.ReleaseDocument;
import org.springframework.data.domain.Page;

public interface ReleaseDocumentService  extends BaseService<ReleaseDocumentDto, String> {

    ReleaseDocumentDto findByCode(String code) throws NotFoundException;


    Page<ReleaseDocument> getpages(int pageSize, int pageNumber, String filter, String farmername);
    Page<ReleaseDocument> getpages1(int pageSize, int pageNumber, String filter );

    Page<ReleaseDocument> getpagesarchive(int pageSize, int pageNumber, String filter);
    Page<ReleaseDocument> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;

}