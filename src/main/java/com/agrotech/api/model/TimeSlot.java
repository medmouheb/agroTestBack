package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {
    @Field("listeDesJours")
    private String listeDesJours;

    @Field("heureDebut")
    private String heureDebut;

    @Field("heureFin")
    private String heureFin;


    public String getListeDesJours() {
        return listeDesJours;
    }

    public void setListeDesJours(String listeDesJours) {
        this.listeDesJours = listeDesJours;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
}



