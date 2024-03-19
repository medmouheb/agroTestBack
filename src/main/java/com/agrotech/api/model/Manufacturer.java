package com.agrotech.api.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Manufacturers")
public class Manufacturer {


    @Id
    private String id;

    private String farmer;

    @NotNull
    @Size(max = 50)
    private String manufacturerName;


    @NotNull
    @Size(max = 50)
    private String manufacturerCode;

    private boolean active=true;

    @Size(max = 200)
    private String notes;
}
