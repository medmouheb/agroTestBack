package com.agrotech.api.dto;

import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.Tax;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto extends BaseDto {

    private String code ;
    private String name;
    private String type ;
    private String currency ;
    private List<Tax> taxes = new ArrayList<> ();

    private String Payment_Term ;
    private Boolean isDeleted=false;

    private String buyer;



    private String product;


    public  String toStrings() {
        return "SalesDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", currency='" + currency + '\'' +
                ", taxes=" + taxes +
                ", Payment_Term='" + Payment_Term + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
