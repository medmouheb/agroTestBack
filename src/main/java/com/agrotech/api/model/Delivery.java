package com.agrotech.api.model;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="deliveryService")
public class Delivery  extends BaseEntity {

    @Size(max = 50)
    @Indexed(unique = true)
    private String name;


    @Indexed(unique = true)
    @Size(max = 5)
    private String code;


    private Map<String, String> weights  = new HashMap<>();
    private Map<String, String> distances  = new HashMap<>();
    private Map<String, String> types  = new HashMap<>();

    private String contactPhone;
    private String contactEmail;
    private String address;

    private String farmer;

    private boolean isDeleted=false;

    public boolean getIsDeleted() {
        return isDeleted;
    }




    public void setIsDeleted(boolean b) {
    }
}
