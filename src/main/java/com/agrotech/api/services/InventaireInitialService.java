package com.agrotech.api.services;

import com.agrotech.api.dto.InventaireInitialDto;
import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.InventaireInitial;
import com.agrotech.api.model.LogisticUnit;
import org.springframework.data.domain.Page;

public interface InventaireInitialService extends BaseService<InventaireInitialDto,String>{

    InventaireInitialDto findByCodeProduit(String codeProduit) throws NotFoundException;
    Page<InventaireInitialDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<InventaireInitial> getpages(int pageSize, int pageNumber, String filter) ;
    Page<InventaireInitial> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    InventaireInitial findByNomDuProduit(String nomDuProduit)throws NotFoundException;
    public Page<InventaireInitialDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<InventaireInitialDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}
