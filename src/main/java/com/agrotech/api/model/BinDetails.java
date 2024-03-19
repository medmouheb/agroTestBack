package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BinDetails extends BaseEntity {
    private Number bin;

    private Double capacity;

    private Boolean isDeleted=false;
    private String farmer;

}
