package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationManagementDto extends BaseDto{

    @Id
    @Indexed(unique = true)
    private String operationId;

    private String farmer;

    private String typeOperation;


    @Indexed(unique = true)
    private String nomOperation;

    private String business;

    private Number dureeEstimee;

    private LocalDate dateDebutDeOperation;

    private LocalTime heureDebutDeOperation;

    private LocalDate dateDeFinDeOperation;

    private LocalTime heureFinDeOperation;

    private String dureeReelle;

    private List<String> ressourcesUtilisees;

    private Double coutEstime;

    private Double coutReel;

    private Boolean isDeleted=false;
}
