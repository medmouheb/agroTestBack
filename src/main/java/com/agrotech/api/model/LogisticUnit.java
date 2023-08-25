package com.agrotech.api.model;

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
@Document(collection = "Logistic-Unit")
public class LogisticUnit extends BaseEntity {

    @Indexed(unique = true)
    private String logisticCode;

    @Indexed(unique = true)

    private String logisticName;

    private String companyName;

    private String divisionName;

    private Boolean isDeleted=false;

}
