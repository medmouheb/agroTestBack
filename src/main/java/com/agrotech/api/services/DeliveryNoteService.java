package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.DeliveryNoteDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.DeliveryNote;
import org.springframework.data.domain.Page;

public interface DeliveryNoteService extends BaseService<DeliveryNoteDto, String> {

    DeliveryNoteDto findByCode(String code, String farmer) throws NotFoundException;


    Page<DeliveryNote> getpages(int pageSize, int pageNumber, String filter, String farmername);
    Page<DeliveryNote> getpages1(int pageSize, int pageNumber, String filter );

    Page<DeliveryNote> getpagesarchive(int pageSize, int pageNumber, String filter);
    Page<DeliveryNote> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;

}
