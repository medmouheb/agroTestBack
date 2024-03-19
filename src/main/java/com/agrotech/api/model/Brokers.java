package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Document(collection = "Brokers")
public class Brokers extends BaseEntity{


    @NotBlank(message = "BrokerCode is required")
    @Size(max = 50)
    @Indexed(unique = true)
    private  String BrokerCode;

    @NotBlank(message = "BrokerCode is required")
    @Size(max = 50)
    private  String BrokerName;

    private String DivisionCode;

    private String DivisionName;

    private Boolean Active;

    @Size(max = 100)
    private String Notes;
    private String farmer;

    private Boolean isDeleted=false;
}
