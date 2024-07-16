package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypeHistoryIInvoicesDto;
import com.agrotech.api.model.VendorTypeHistoryIInvoices;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VendorTypeHistoryIInvoicesMapper extends BaseMapper<VendorTypeHistoryIInvoicesDto,VendorTypeHistoryIInvoices>{
}
