package com.agrotech.api.dto;


import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.model.Produit;
import com.agrotech.api.model.Tax;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyDto extends BaseDto {

    private String code ;
    private LocalDateTime buyDate;
    private int  totalPrice;
    private String productType;
    private List<Tax> taxes = new ArrayList<> ();
    private int  payed;
    private int  remain;
    private String buyStatus;
    private Fournisseur fournisseur;
    private Produit produit;

    private String purchaseOrder;
    private String purchaseInvoice;
    private String paymentReceipt;
    private String paymentNotice;
    private String bankStatement;



    private Boolean isDeleted=false;


}
