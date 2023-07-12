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
@Document(collection="willaya")
public class Willaya extends BaseEntity{

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 10, message = ValidationMessages.CODE_TOO_LONG)
    private String code ;
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 10, message = ValidationMessages.NAME_TOO_LONG)
    private String name ;
    private Boolean isDeleted=false;
}
