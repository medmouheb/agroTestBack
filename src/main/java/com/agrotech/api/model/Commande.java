package com.agrotech.api.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.utils.ValidationMessages;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "commande")

public class Commande  extends BaseEntity{
   // private String id;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String paymentTermCode;
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.NAME_TOO_LONG)
    private String paymentTermName;

    @NotNull(message = ValidationMessages.DATE_SOLD_REQUIRED)
    private Date dateSold;
    @NotNull(message = ValidationMessages.DAYS_AFTER_REQUIRED)
    private String daysAfterCurrentMonth;
    @NotNull(message = ValidationMessages.DATE_ship_REQUIRED)
    private Date deliveryDate;
    @NotNull(message = ValidationMessages.DATE_inv_REQUIRED)
    private Date invoiceDate;
    @NotNull(message = ValidationMessages.DATE_rec_REQUIRED)
    private Date receiveDate;
    @NotNull(message = ValidationMessages.DATE_exp_REQUIRED)
    private Date shipDate;
    // Optional Fields
    private Integer termDays;
    private Integer termMonth;
    private Double discountRate;
    private Integer discountDays;
    private Boolean prepay;
    private String notes;
    private Boolean schedulePay;
    private String taxDistribution;
    private String scheduleBasis;

    private PaymentTerm paymentTerm;
    private Boolean isDeleted=false;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentTerm {
     @NotBlank(message = ValidationMessages.COUNT_REQUIRED)
        private Integer paymentCount;
     @NotBlank(message = ValidationMessages.SCHEDULE_REQUIRED)
        private String scheduleBasisUnit;
     @NotBlank(message = ValidationMessages.PAYMENT_RATE_REQUIRED)
        private Double paymentRate;
    }

}