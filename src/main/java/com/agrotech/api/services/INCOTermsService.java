package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.INCOTermsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.INCOTerms;
import org.springframework.data.domain.Page;

public interface INCOTermsService extends BaseService <INCOTermsDto, String>{


    INCOTermsDto findByINCOTermCode(String INCOTermCode) throws NotFoundException;
    Page<INCOTermsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<INCOTerms> getpages(int pageSize, int pageNumber, String filter) ;
    Page<INCOTerms> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    INCOTerms findByINCOTermName(String INCOTermName)throws NotFoundException;
    public Page<INCOTermsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<INCOTermsDto> findArchivedPage(int pageSize, int pageNumber, String filter);


}
