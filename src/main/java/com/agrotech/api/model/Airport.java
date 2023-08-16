package com.agrotech.api.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Airports")
public class Airport {



        @Id
        private String id;

        @NotNull
        @Size(max = 50)
        private String airportCode;

        @Size(max = 50)
        @NotNull
        private String airportName;


        private boolean active=true;

        @Size(max = 200)
        private String notes;



}
