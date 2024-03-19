package com.agrotech.api.model;


import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "tax")
public class Tax  extends BaseEntity{

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String name ;
    private int percentage;
    private String description;
    private Boolean isDeleted=false;
    private String farmer;

}
