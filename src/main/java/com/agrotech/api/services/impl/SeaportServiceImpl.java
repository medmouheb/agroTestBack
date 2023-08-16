package com.agrotech.api.services.impl;

import com.agrotech.api.model.Seaport;
import com.agrotech.api.Repository.SeaportRepo;
import com.agrotech.api.services.SeaportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SeaportServiceImpl implements SeaportService {

    @Autowired
    private SeaportRepo seaportRepository;

    @Override
    public Seaport ajouterSeaport(Seaport seaport) {
        return seaportRepository.save(seaport);
    }

    @Override
    public Seaport modifierSeaport(Seaport seaport) {
        return seaportRepository.save(seaport);
    }

    @Override
    public List<Seaport> getActiveTrueSeaports() {
        return seaportRepository.findSeaportByActiveTrue();
    }

    @Override
    public void supprimerById(String id) {
        seaportRepository.deleteById(id);
    }

    @Override
    public boolean seaportExists(String id) {
        return seaportRepository.existsById(id);
    }

    @Override
    public boolean seaportCodeExists(String seaPortCode) {
        return seaportRepository.existsBySeaportCode(seaPortCode);
    }

    @Override
    public Seaport getSeaportById(String id) {
        return seaportRepository.findById(id).get();
    }

    @Override
    public Seaport getSeaportBySeaportCode(String seaportCode) {
        return seaportRepository.findBySeaportCode(seaportCode);
    }

    @Override
    public List<Seaport> SearchSeaportByNameAndActive(String seaportName) {
        return seaportRepository.findSeaportBySeaportNameContainingIgnoreCaseAndActiveTrue(seaportName);
    }

    @Override
    public List<Seaport> SearchSeaportByNameAndArchived(String seaportName) {
        return seaportRepository.findSeaportBySeaportNameContainingIgnoreCaseAndActiveFalse(seaportName);
    }

    @Override
    public List<Seaport> getArchivedSeaports() {
        return seaportRepository.findSeaportByActiveFalse();
    }


}
