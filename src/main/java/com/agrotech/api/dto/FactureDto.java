package com.agrotech.api.dto;

import com.agrotech.api.model.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureDto  extends BaseDto{
    @Indexed(unique = true)
    @Size(max = 5)
    private String code;
    private String description;

    private ShipMethods shipMethods;
    private float shippingPrice;

    private PaymentMethod paymentMethod;
    private String paymentMethodNotes;

    private int InstallmentsNumber;
    private float exchangeRate;

    private Buyers buyer;

    private List<DevisProduct> produits;

    private boolean sendMail;

    private String emailSubject;
    private String emailBody;

    private String farmer;

    private Boolean isDeleted=false;

    private LocalDateTime lastUpdate=LocalDateTime.now();
    private Date availableTo;

    private String factureStatus;

    private String file;

    private Devis devis;
}
