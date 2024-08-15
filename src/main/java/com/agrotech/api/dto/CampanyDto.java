package com.agrotech.api.dto;

import com.agrotech.api.model.User;
import org.springframework.data.mongodb.core.index.Indexed;

import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampanyDto extends BaseDto{


    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    private String name="" ;
    @Size(max = 10)
    private String cityCode ;
    @Size(max = 10)
    private String country ;
    @Size(max = 100)
    private String cityName ;
    @Size(max = 3)
    private String state ;

    @Size(max = 150)
    private String email ;

    @Size(max = 150)
    private String address1 ;

    @Size(max = 150)
    private String address2 ;
    @Size(max = 150)
    private String phone1 ;

    @Size(max = 150)
    private String phone2 ;

    @Size(max = 150)
    private String webSite ;

    private Boolean isDeleted=false;


    private String farmer;




    private HashSet<User> employees = new HashSet<>();


    private String picture;

}
