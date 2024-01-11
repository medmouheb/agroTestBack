package com.agrotech.api.dto;

import com.agrotech.api.model.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriversDto extends BaseDto{

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
