package com.agrotech.api.services;

import com.agrotech.api.dto.PaymentMethodDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.PaymentMethod;
import org.springframework.data.domain.Page;

public interface PaymentMethodService  extends BaseService<PaymentMethodDto, String>  {
    PaymentMethodDto findByCode(String code) throws NotFoundException;
    Page<PaymentMethodDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<PaymentMethod> getpages(int pageSize, int pageNumber, String filter) ;
    Page<PaymentMethod> getpagesarchive(int pageSize, int pageNumber, String filter) ;
    public void archive(String id) throws NotFoundException;
    public void setNotArchive(String id) throws NotFoundException;
    PaymentMethod findByname(String name)throws NotFoundException;
    public Page<PaymentMethodDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<PaymentMethodDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}
