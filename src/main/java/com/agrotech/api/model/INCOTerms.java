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
@Document(collection = "INCOTerms")
public class INCOTerms extends BaseEntity{



    private String TransportType;

    @NotBlank(message = "INCOTermCode is required")
    @Indexed(unique = true)
    @Size(max = 50)
    private String INCOTermCode;


    @Size(max = 50)
    private String INCOTermName;



    private Boolean Active;


    @Size(max = 200)
    private String Notes;

    private String farmer;

    private Boolean isDeleted=false;

}
