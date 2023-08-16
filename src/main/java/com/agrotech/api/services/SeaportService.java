package com.agrotech.api.services;

import com.agrotech.api.model.Airport;
import com.agrotech.api.model.Seaport;

import java.util.List;

public interface SeaportService {

    Seaport ajouterSeaport(Seaport seaport);
    Seaport modifierSeaport(Seaport seaport);
    List<Seaport> getActiveTrueSeaports();
    void supprimerById(String id);
    boolean seaportExists(String id);
    boolean seaportCodeExists(String seaPortCode);
    Seaport getSeaportById(String id);
    Seaport getSeaportBySeaportCode(String seaportCode);
    List<Seaport>SearchSeaportByNameAndActive(String seaportName);
    List<Seaport>SearchSeaportByNameAndArchived(String seaportName);

    List<Seaport>getArchivedSeaports();

}
