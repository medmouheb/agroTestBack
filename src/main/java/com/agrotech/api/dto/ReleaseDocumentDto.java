package com.agrotech.api.dto;


import com.agrotech.api.model.BaseEntity;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseDocumentDto extends BaseEntity {

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;

    private String farmer;

    private Boolean isDeleted=false;

    private String additionalNotes;

    private String issuer;

    private String purpose;

    private String usageDetails;


    private String stock;

    private String product;

    private String description;
}
