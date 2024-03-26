package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.ContactRepository;
import com.agrotech.api.Repository.CropRepo;
import com.agrotech.api.dto.ContactDto;
import com.agrotech.api.dto.CropDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.ContactMapper;
import com.agrotech.api.mapper.CropMapper;
import com.agrotech.api.model.Contact;
import com.agrotech.api.model.Crop;
import com.agrotech.api.services.ContactService;
import com.agrotech.api.services.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContactServiceImpl  implements ContactService {

    @Autowired
    private ContactRepository contactRepository ;

    @Autowired
    private ContactMapper contactMapper ;

    public Contact save(Contact dto) {

        return contactRepository.save(dto);

    }


    @Override
    public ContactDto create(ContactDto dto) {
        return contactMapper.toDto(save(contactMapper.toEntity(dto)));

    }



    @Override
    public ContactDto update(String id, ContactDto dto) throws NotFoundException {

        Optional<Contact> camOptional =  contactRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Contact not found ");
        }

        Contact campanyExisting = camOptional.get();
        contactMapper.partialUpdate(campanyExisting, dto);

        return contactMapper.toDto(save(campanyExisting));

    }

    @Override
    public ContactDto findById(String id) throws NotFoundException {
        Optional<Contact> campOptional = contactRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Contact not found ");
        }
        return contactMapper.toDto(campOptional.get());
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!contactRepository.existsById(id)) {
            throw new NotFoundException("Contact not found ");
        }

        contactRepository.deleteById(id);


    }


    @Override
    public ContactDto findByCode(String code) throws NotFoundException {
        Optional<Contact> campOptional = contactRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Contact not found ");
        }
        return contactMapper.toDto(campOptional.get());
    }


    @Override
    public Page<Contact> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  contactRepository.findByIsDeletedAndFarmerAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(false,filter,filter,filter,farmername, pageable);


    }

    @Override
    public Page<Contact> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  contactRepository.findByIsDeletedAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(false,filter,filter,filter, pageable);


    }

    @Override
    public Page<Contact> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  contactRepository.findByIsDeletedAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(true,filter,filter,filter, pageable);

    }

    @Override
    public Page<Contact> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  contactRepository.findByIsDeletedAndFarmerAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(true,filter,filter,filter,farmername, pageable);

    }


    @Override
    public Page<ContactDto> findPage(int pageSize, int pageNumber, String filter) {

        return null;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Contact> groOptional =  contactRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Contact not found ");
        }
        Contact groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        contactRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Contact> groOptional =  contactRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Contact not found ");
        }
        Contact groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        contactRepository.save(groExisting);

    }










}
