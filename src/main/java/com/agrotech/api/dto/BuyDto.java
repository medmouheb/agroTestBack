package com.agrotech.api.dto;


import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.model.Produit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyDto extends BaseDto {

    private String code ;
    private LocalDateTime buyDate;
    private int  totalPrice;
    private String productType;
    //    private ArrayList<Taxe> taxes = new ArrayList<Taxe>();
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
