package com.agrotech.api.dto;


import com.agrotech.api.model.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;



public class TimeSlotDto extends TimeSlot {

    private String listeDesJours;

    private String heureDebut;

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
