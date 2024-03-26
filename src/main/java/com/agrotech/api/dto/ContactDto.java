package com.agrotech.api.dto;


import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto  extends BaseDto {

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;

    private String firstName ;
    private String lastName ;
    private String email1;
    private String email2;
    private String avatar;
    private String card;
    private String phone1;
    private String phone2;
    private List<String> tags;
    private String address;
    private String city;
    private String state;
    private String country;
    private String latitude;
    private String longitude;
    private String Industry;
    private String notes;
    private String label;
    private Boolean isDeleted=false;

    private String Farmer;

    private LocalDateTime lastUpdate=LocalDateTime.now();

}
