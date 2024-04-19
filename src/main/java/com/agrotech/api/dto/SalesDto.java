package com.agrotech.api.dto;

import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.Tax;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto extends BaseDto {
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String code ;
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    private String name;
    private String type ;
    private String currency ;
    private String Payment_Term ;
    private Boolean isDeleted=false;

    private  String buyer;

    private List<Tax> taxes = new ArrayList<>();



    private String purchaseOrder;
    private String quotation;
    private String salesInvoice;
    private String deliveryNote;
    private String creditNote;

    private String product;
    private int quantity=0;

    private String farmer;


    public  String toStrings() {
        return "SalesDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", currency='" + currency + '\'' +
                ", taxes=" + taxes +
                ", Payment_Term='" + Payment_Term + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
