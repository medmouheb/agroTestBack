package com.agrotech.api.dto;

import lombok.Data;
import java.io.Serializable;


@Data
public class AirportDTO implements Serializable {


    private String id;
    private String airportCode;
    private String airportName;
    private boolean active;
    private String notes;
    private String farmer;


}