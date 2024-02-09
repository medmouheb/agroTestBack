package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.StockRepository;
import com.agrotech.api.dto.StockDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.StockMapper;
import com.agrotech.api.model.Stock;
import com.agrotech.api.services.StockServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockServices {



    @Autowired
    private StockRepository stockRepository ;
    @Autowired
    private StockMapper stockMapper ;



    public Stock save(Stock dto) {

        return stockRepository.save(dto);

    }


    @Override
    public StockDTO create(StockDTO dto) {
        return stockMapper.toDto(save(stockMapper.toEntity(dto)));

    }



    @Override
    public StockDTO update(String id, StockDTO dto) throws NotFoundException {

        Optional<Stock> camOptional =  stockRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Stock not found ");
        }

        Stock campanyExisting = camOptional.get();
        stockMapper.partialUpdate(campanyExisting, dto);

        return stockMapper.toDto(save(campanyExisting));

    }

    @Override
    public StockDTO findById(String id) throws NotFoundException {
        Optional<Stock> campOptional = stockRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Stock not found ");
        }
        return stockMapper.toDto(campOptional.get());
    }

    @Override
    public List<StockDTO> findAll() {
        return stockRepository.findAll().stream()
                .map(stockMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!stockRepository.existsById(id)) {
            throw new NotFoundException("Stock not found ");
        }

        stockRepository.deleteById(id);


    }


    @Override
    public StockDTO findByCode(String code) throws NotFoundException {
        Optional<Stock> campOptional = stockRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Stock not found ");
        }
        return stockMapper.toDto(campOptional.get());
    }
    @Override
    public Page<StockDTO> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<StockDTO> result =  stockRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable)
                .stream()
                .map(stockMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<Stock> getpages(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());

        return stockRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
    }

    @Override
    public Page<Stock> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());

        return stockRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
    }

    @Override
    public Page<StockDTO> findPage(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<StockDTO>  result = stockRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .map(stockMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Stock> groOptional =  stockRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Stock not found ");
        }
        Stock groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        stockRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Stock> groOptional =  stockRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Stock not found ");
        }
        Stock groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        stockRepository.save(groExisting);

    }

    @Override
    public Stock findByname(String name) throws NotFoundException {
        return stockRepository.findByName(name);
    }

    @Override
    public Page<StockDTO> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<StockDTO>  result = stockRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(stockMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public List<Stock> findBynamee() throws NotFoundException {
        return stockRepository.findAll();


    }

    @Override
    public Page<StockDTO> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<StockDTO>  result = stockRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                .map(stockMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
