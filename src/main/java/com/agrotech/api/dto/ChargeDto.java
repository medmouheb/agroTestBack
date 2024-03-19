package com.agrotech.api.dto;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "charge")
public class ChargeDto extends BaseDto{



    //private Long id;

    private String farmer;

    private String suppChargeType;

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String suppNo;

    private String suppName;

    private String suppType;


    private String chargeCalculationType;

    private String vendorCalculationType;



    private String transactionBase;
    private boolean active;
    private String notes;
}
