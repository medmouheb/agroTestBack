package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.ContactDto;
import com.agrotech.api.dto.CropDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Contact;
import com.agrotech.api.model.Crop;
import org.springframework.data.domain.Page;

public interface ContactService extends BaseService<ContactDto, String> {
    ContactDto findByCode(String code) throws NotFoundException;


    Page<Contact> getpages(int pageSize, int pageNumber, String filter, String farmername);
    Page<Contact> getpages1(int pageSize, int pageNumber, String filter );

    Page<Contact> getpagesarchive(int pageSize, int pageNumber, String filter);
    Page<Contact> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

    public void archive(String id) throws NotFoundException;

    public void setNotArchive(String id) throws NotFoundException;
}
