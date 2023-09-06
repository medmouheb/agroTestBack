package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "OperationManagement")
public class OperationManagement {

    @Id
    @Indexed(unique = true)
    private String operationId;

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
