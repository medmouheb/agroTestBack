package com.agrotech.api.services;

import com.agrotech.api.model.Airport;
import com.agrotech.api.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {


    Manufacturer ajouterManufacturer(Manufacturer manufacturer);
    Manufacturer modifierManufacturer(Manufacturer manufacturer);
    List<Manufacturer> getActiveTrueManufacturers();
    void supprimerById(String id);
    boolean manufacturerExists(String id);
    boolean manufacturerCodeExists(String manufacturerCode);
    List<Manufacturer>SearchManufacturerByNameAndActive(String manufacturerName);
    List<Manufacturer>SearchManufacturerByNameAndArchived(String manufacturerName);

    Manufacturer getManufacturerById(String id);
    Manufacturer getManufacturerByManufacturerCode(String manufacturerCode);
    List<Manufacturer> getArchivedManufacturers();

}
