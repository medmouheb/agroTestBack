package com.agrotech.api.model;

import com.agrotech.api.utils.ValidationMessages;
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
@Document(collection="deliveryInstruction")
public class DeliveryInstruction extends BaseEntity {
    @Indexed(unique = true)
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    private String  productType ;
    @Size(max = 50)
    @Indexed(unique = true)

    private String instructiuonName;
    @Indexed(unique = true)
    @Size(max = 5)


    private String instructiuonCode;

    private String farmer;

    private Boolean isDeleted=false;
    @Size(max = 200)

    private String  notes;
    private Boolean active;

}
