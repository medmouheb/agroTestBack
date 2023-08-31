package com.agrotech.api.services;

import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.dto.UtilisationDuProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.UtilisationDuProduit;
import org.springframework.data.domain.Page;

public interface UtilisationDuProduitService extends BaseService<UtilisationDuProduitDto,String>{

    UtilisationDuProduitDto findByCodeProduit(String codeProduit) throws NotFoundException;
    Page<UtilisationDuProduitDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<UtilisationDuProduit> getpages(int pageSize, int pageNumber, String filter) ;
    Page<UtilisationDuProduit> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    UtilisationDuProduit findByNomDuProduit(String nomDuProduit)throws NotFoundException;
    public Page<UtilisationDuProduitDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<UtilisationDuProduitDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}
