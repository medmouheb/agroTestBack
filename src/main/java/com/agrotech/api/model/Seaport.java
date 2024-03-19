package com.agrotech.api.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "seaports")
public class Seaport {

    @Id
    private String id;

    @NotNull
    @Size(max = 50)
    private String seaportCode;

    @NotNull
    @Size(max = 50)
    private String seaportName;


    private Boolean active=true;


    @Size(max = 200)
    private String notes;

    private String farmer;

}
