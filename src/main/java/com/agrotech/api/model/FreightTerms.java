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
@Document(collection="Freight-Terms")
public class FreightTerms extends BaseEntity {
    @Indexed(unique = true)
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Size(max = 50)
    private String  freighttermcode ;

    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Size(max = 50)
    private String  freighttermname ;

    private Boolean Active;

    @Size(max = 200)
    private String notes;

    private Boolean isDeleted=false;

    // Add getter and setter methods for 'isDeleted'


}
