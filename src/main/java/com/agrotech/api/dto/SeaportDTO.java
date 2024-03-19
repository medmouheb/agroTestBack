package com.agrotech.api.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class SeaportDTO implements Serializable {


    private String id;
    private String seaportCode;
    private String seaportName;
    private boolean active;
    private String notes;

    private String farmer;

}
