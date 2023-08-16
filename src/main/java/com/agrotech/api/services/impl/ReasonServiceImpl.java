package com.agrotech.api.services.impl;

import com.agrotech.api.model.Reason;
import com.agrotech.api.Repository.ReasonRepo;
import com.agrotech.api.services.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReasonServiceImpl implements ReasonService {



    @Autowired
    private ReasonRepo reasonRepository;

    @Override
    public Reason ajouterReason(Reason reason) {
        return reasonRepository.save(reason);
    }

    @Override
    public Reason modifierReason(Reason reason) {
        return reasonRepository.save(reason);
    }

    @Override
    public List<Reason> getActiveTrueReasons() {
        return reasonRepository.findReasonByActiveTrue();
    }

    @Override
    public void supprimerById(String id) {
        reasonRepository.deleteById(id);
    }

    @Override
    public boolean reasonExists(String id) {
        return reasonRepository.existsById(id);
    }

    @Override
    public boolean reasonCodeExists(String reasonCode) {
        return reasonRepository.existsByReasonCode(reasonCode);
    }

    @Override
    public Reason getReasonById(String id) {
        return reasonRepository.findById(id).get();
    }

    @Override
    public Reason getReasonByReasonCode(String reasonCode) {
        return reasonRepository.findByReasonCode(reasonCode);
    }

    @Override
    public List<Reason>SearchReasonByNameAndActive(String reasonName) {
        return reasonRepository.findReasonByReasonNameContainingIgnoreCaseAndActiveTrue(reasonName);
    }

    @Override
    public List<Reason> SearchReasonByNameAndArchived(String reasonName) {
        return reasonRepository.findReasonByReasonNameContainingIgnoreCaseAndActiveFalse(reasonName);
    }

    @Override
    public List<Reason> getArchivedReasons() {
        return reasonRepository.findReasonByActiveFalse();
    }
}
