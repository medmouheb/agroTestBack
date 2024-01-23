package com.agrotech.api.dto;


import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto extends BaseDto{

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;
    private LocalDateTime buyDate;
    private int  totalPrice;


    //    private ArrayList<Taxe> taxes = new ArrayList<Taxe>();
    private int  payed;
    private int  remain;
    private String serviceStatus;

    private Boolean isDeleted=false;


    private String purchaseOrder;
    private String quote;
    private String salesInvoice;
    private String deliveryNote;
    private String have;
}
