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
@Document(collection = "payemetMethod")
public class PaymentMethod  extends BaseEntity{
    @NotBlank()
    @Indexed(unique = true)
    @Size(max = 50)
    private String code ;
    @NotBlank()
    @Size(max = 250)
    private String name ;

    private String notes;
    private Boolean active ;

    private String farmer;


    private Boolean isDeleted=false;
}
