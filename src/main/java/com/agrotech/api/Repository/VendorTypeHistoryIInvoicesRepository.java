package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeHistoryIInvoices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VendorTypeHistoryIInvoicesRepository extends MongoRepository<VendorTypeHistoryIInvoices, String> {


    Optional<VendorTypeHistoryIInvoices> findByInvoiceCode(String InvoiceCode);
    Page<VendorTypeHistoryIInvoices> findByInvoiceTypeContainingIgnoreCase(String InvoiceType, Pageable pageable);
    Page<VendorTypeHistoryIInvoices> findByIsDeletedAndInvoiceTypeContainingIgnoreCase(Boolean isDeleted, String InvoiceType, Pageable pageable);
    Page<VendorTypeHistoryIInvoices> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorTypeHistoryIInvoices findByInvoiceType(String InvoiceType );
}
