package com.agrotech.api.services;

import com.agrotech.api.model.Airport;
import com.agrotech.api.model.Reason;

import java.util.List;

public interface ReasonService {

    Reason ajouterReason(Reason reason);
    Reason modifierReason(Reason reason);
    List<Reason> getActiveTrueReasons();
    void supprimerById(String id);
    boolean reasonExists(String id);
    boolean reasonCodeExists(String reasonCode);
    Reason getReasonById(String id);
    Reason getReasonByReasonCode(String reasonCode);
    List<Reason>SearchReasonByNameAndActive(String reasonName);
    List<Reason>SearchReasonByNameAndArchived(String reasonName);

    List<Reason>getArchivedReasons();

}
