package com.agrotech.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Buy")
public class Buy  extends BaseEntity{
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;
    private LocalDateTime buyDate;
    private int  totalPrice;
    private String ProductType;
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
