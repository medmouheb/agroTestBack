package com.agrotech.api.model;


import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "production")
public class Production extends BaseEntity{

    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    private String name;


    private Date  startDate;
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
