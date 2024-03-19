package com.agrotech.api.model;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "charge")

public class Charge extends BaseEntity {


    //private Long id;
    @NotNull(message = ValidationMessages.TYPE_REQUIRED)
    private String suppChargeType;

    private String farmer;

    private String suppNo;

    @NotNull(message = ValidationMessages.NAME_REQUIRED)

    private String suppName;

    @NotNull(message = ValidationMessages.TYPE_REQUIRED)

    private String suppType;
    @NotNull(message = ValidationMessages.CHARGECALCULATIONTYPE_REQUIRED)

    private String chargeCalculationType;

    @NotNull(message = ValidationMessages.VENDORCALCULATIONTYPE_REQUIRED)

    private String vendorCalculationType;


    private String transactionBase;
    private boolean active;
    private String notes;
    private Boolean isDeleted=false;




}
