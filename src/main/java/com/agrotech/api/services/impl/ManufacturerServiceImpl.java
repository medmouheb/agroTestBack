package com.agrotech.api.services.impl;

import com.agrotech.api.model.Manufacturer;
import com.agrotech.api.Repository.ManufacturerRepo;
import com.agrotech.api.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

    @Autowired
    ManufacturerRepo manufacturerRepository;
    @Override
    public Manufacturer ajouterManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer modifierManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> getActiveTrueManufacturers() {
        return manufacturerRepository.findManufacturerByActiveTrue();
    }

    @Override
    public void supprimerById(String id) {
        manufacturerRepository.deleteById(id);
    }

    @Override
    public boolean manufacturerExists(String id) {
        return manufacturerRepository.existsById(id);
    }

    @Override
    public boolean manufacturerCodeExists(String manufacturerCode) {
        return manufacturerRepository.existsByManufacturerCode(manufacturerCode);
    }

    @Override
    public List<Manufacturer> SearchManufacturerByNameAndActive(String manufacturerName) {
        return manufacturerRepository.findManufacturerByManufacturerNameContainingIgnoreCaseAndActiveTrue(manufacturerName);
    }

    @Override
    public List<Manufacturer> SearchManufacturerByNameAndArchived(String manufacturerName) {
        return manufacturerRepository.findManufacturerByManufacturerNameContainingIgnoreCaseAndActiveFalse(manufacturerName);
    }

    @Override
    public Manufacturer getManufacturerById(String id) {
        return manufacturerRepository.findById(id).get();
    }

    @Override
    public Manufacturer getManufacturerByManufacturerCode(String manufacturerCode) {
        return manufacturerRepository.findByManufacturerCode(manufacturerCode);
    }

    @Override
    public List<Manufacturer> getArchivedManufacturers() {
        return manufacturerRepository.findManufacturerByActiveFalse();
    }
}
