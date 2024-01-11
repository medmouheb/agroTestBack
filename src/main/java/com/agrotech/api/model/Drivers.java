package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "drivers")
public class Drivers extends BaseEntity{

    private String typeDeDriver;

    @Indexed(unique = true)
    private String codeEmploye;
    @Indexed(unique = true)
    private String nomDuChauffeur;

    private String division;

    private Double nombreMaximumHeureTravailles;


    private List<TimeSlot> workingtime;
    private List<String> listeDesJours;

    private List<String> heureDebut;

    private List<String> heureFin;

    private LocalDate DriverLicenceExpiration;

    private Double coutHoraire;


    private String driverLicense;


    private Boolean isDeleted=false;


}
