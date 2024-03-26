package com.agrotech.api.dto;

import com.agrotech.api.model.Warehouse;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionDto  extends BaseDto {
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    private String name;


    private Date startDate;
    private Date  endDate;


    private String titled;
    private String project;


    private Warehouse warehouse;

    private String numeroLot;
    private Date  expDate;
    private String  etatProd;

    private Boolean isDeleted=false;
    private String farmer;

}
