package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehicles")
public class Vehicles extends BaseEntity{

    private String typeDeVehicule;

    @Indexed(unique = true)
    private String codeVehicule;

    private String nomDuVehicule;

    private String logisticCode;

    private Double weightCapacity;

    private Boolean utilisation;

    private String coutHoraire;

    private String nomOperationmaintenance;

    private String numerooperationmaintenance;

    private Date dateDerealisation;

    private String KilometrageDerealisation;

    private String listeDesOperationsEffectuees;

    private String papiersDuVehicule;


    private Boolean isDeleted=false;
    private String farmer;

}
