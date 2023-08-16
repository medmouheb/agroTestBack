package com.agrotech.api.dto;

import lombok.Data;
import java.io.Serializable;


@Data
public class ReasonDTO implements Serializable {

    private String id;
    private String reasonCode;
    private String reasonName;
    private boolean active;
    private String notes;
}
