package com.agrotech.api.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.SalesRepository;
import com.agrotech.api.dto.SalesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.SalesMapper;
import com.agrotech.api.services.SalesServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesServices {

	@Autowired
    private final SalesRepository salesRepository;
	@Autowired
    private final SalesMapper salesMapper;

    @Autowired
    private final BuyersRepository buyersRepository;

    @Autowired
    private final ProduitRepository produitRepository;

    @Autowired
    private final NotificationService notificationService;

    public Sales save(Sales sales) {
        return salesRepository.save(sales);
    }

    @Override
    public SalesDto create(SalesDto dto) {
        Buyers b= buyersRepository.findById(dto.getBuyer()).get()  ;
        Produit p= produitRepository.findById(dto.getProduct()).get() ;
        TurnoverHistory th=new TurnoverHistory();

        BigDecimal bigDecimalValue = new BigDecimal(dto.getQuantity());
        try {
            float totalPrice=  bigDecimalValue.multiply( p.getPrixUnitaireHt()).floatValue() ;
            th.setAmount(totalPrice);

            th.setOldTurnver(b.getTurnover());
            b.setTurnover(b.getTurnover()-totalPrice);
        }catch (Exception e){}


        th.setNewTurnver(b.getTurnover());

        th.setMovementType("sales");
        th.setMovementName(dto.getName());

        List<TurnoverHistory> thList=b.getTurnoverHistory();
        thList.add(th);
        b.setTurnoverHistory(thList);

        buyersRepository.save(b);
        return salesMapper.toDto(
                save(
                        salesMapper.toEntity(dto)
                )
        );
    }
    @Override
    public SalesDto update(String id, SalesDto dto) throws NotFoundException {
        Optional<Sales> sales = salesRepository.findById(id);

        if (sales.isEmpty()) {
            throw new NotFoundException("Sales not found");
        }

        Sales salesExisting = sales.get();
        salesMapper.partialUpdate(salesExisting, dto);

        return salesMapper.toDto(
                save(salesExisting)
        );
    }

    @Override
    public Page<Sales> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return salesRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
    }
    @Override
    public Page<Sales> findPage1Farmer( String farmer,int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return salesRepository.findByFarmerAndIsDeletedAndNameContainingIgnoreCase(farmer,false,filter, pageable);
    }

    @Override
    public Page<Sales> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return salesRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
    }
    @Override
    public Page<Sales> findArchivedPage1Farmer(String farmer,int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return salesRepository.findByFarmerAndIsDeletedAndNameContainingIgnoreCase(farmer,true,filter, pageable);
    }

    @Override
    public Sales findByname(String name) throws NotFoundException {
        return salesRepository.findByName(name);
    }

    @Override
    public SalesDto findById(String id) throws NotFoundException {
        Optional<Sales> sales = salesRepository.findById(id);

        if (sales.isEmpty()) {
            throw new NotFoundException("Sales not found");
        }
        return salesMapper.toDto(sales.get());
    }

    @Override
    public List<SalesDto> findAll() {
        return salesRepository.findAll()
                .stream()
                .map(salesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesDto> findAllByfarmer(String farmer) {

        return salesRepository.findByFarmer(farmer).stream()
                .map(salesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SalesDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );

        return salesRepository.findAll(pageable)
                .map(salesMapper::toDto);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if (!salesRepository.existsById(id)) {
            throw new NotFoundException("Sales not found");
        }

        salesRepository.deleteById(id);
    }


    @Override
    public Sales saves(Sales sales) {
      return  salesRepository.save(sales);
    }

    @Override
    public SalesDto findByCode(String code, String farmer) throws NotFoundException {
        Optional<Sales> optional = salesRepository.findByCodeAndFarmer(code,farmer);
        if (optional.isEmpty()) {
            throw new NotFoundException("Sales not found");
        }
        return salesMapper.toDto(optional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Sales> groOptional =  salesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Sales groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        salesRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Sales> groOptional =  salesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Sales groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        salesRepository.save(groExisting);

    }

    @Override
    public Page<SalesDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<SalesDto>  result = salesRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(salesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

}
