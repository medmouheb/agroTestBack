package com.agrotech.api.model;


import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reasons")
public class Reason {

    @Id

    private String id;

    @NonNull
    @Size(max = 50)
    private String reasonCode;

    @Size(max = 50)
    @NonNull
    private String reasonName;


    private boolean active=true;

    @Size(max = 200)
    private String notes;

}
