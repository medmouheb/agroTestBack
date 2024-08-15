package com.agrotech.api.mapper;

import com.agrotech.api.dto.ContactDto;
import com.agrotech.api.model.Contact;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ContactMapper  extends BaseMapper<ContactDto, Contact> {
}
