package com.agrotech.api.mapper;


import com.agrotech.api.dto.PaymentMethodDto;
import com.agrotech.api.dto.ShipMethodsDto;
import com.agrotech.api.model.PaymentMethod;
import com.agrotech.api.model.ShipMethods;
import org.mapstruct.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import java.util.Optional;
import org.springframework.data.domain.Pageable;


@Mapper()
@Component
public interface PaymentMethodMapper   extends  BaseMapper <PaymentMethodDto, PaymentMethod> {
}