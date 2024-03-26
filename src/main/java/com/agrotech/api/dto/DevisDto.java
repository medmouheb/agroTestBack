package com.agrotech.api.dto;


import com.agrotech.api.model.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DevisDto  extends  BaseDto{
    @Indexed(unique = true)
    @Size(max = 5)
    private String code;
    private String description;
    private float exchangeRate;
    private ShipMethods shipMethods;
    private float shippingPrice;

    private PaymentMethod paymentMethod;
    private String paymentMethodNotes ;
    private int InstallmentsNumber;

    private Buyers buyer;

    private List<com.agrotech.api.model.DevisProduct> produits;

    private boolean sendMail;

    private String emailSubject;
    private String emailBody;

    private String farmer;
    private LocalDateTime lastUpdate=LocalDateTime.now();

    private Boolean isDeleted=false;

    private Date availableTo;
    private String devisStatus;

    private String file;



}

