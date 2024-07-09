package com.agrotech.api.controller;


import com.agrotech.api.Repository.StockRepository;
import com.agrotech.api.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;


import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "*" }, maxAge = 3600)
@RestController
@RequestMapping("statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StockRepository stockRepository;

    private final TaskRepository taskRepository;

    private final MongoTemplate mongoTemplate;



    @GetMapping("/getStaticsByDate")
    public List<Map> getTaskCountByCreatedAtDate() {
        return taskRepository.countTasksByCreatedAtDate();
    }
    @GetMapping("/getStaticsByOwner")
    public List<Map> getTaskCountByOwner() {
        return taskRepository.countTasksByOwner();
    }

    @GetMapping("/getStaticsByOwnerAndDate")
    public List<Map> countTasksByCreatedAtDateAndOwner(@RequestParam(defaultValue = "") String owner) {
        System.out.println(owner);
        return taskRepository.countTasksByCreatedAtDateAndOwner(owner);
    }
    @GetMapping("/getStaticsByActur")
    public List<Map> countTasksByActur(@RequestParam(defaultValue = "") String owner) {
        return taskRepository.countTasksByActur(owner);
    }
    @GetMapping("/getStaticsByActurAndList")
    public List<Map> countTasksByActurAndList(@RequestParam(defaultValue = "") String owner) {
        return taskRepository.countTasksByActurAndList(owner);
    }

    @GetMapping("/getStockWarehouse")
    public List<Map> getStockWarehouse() {
        return stockRepository.getStockWarehouse();
    }


    @GetMapping("/getStockProduct")
    public List<Map> getStockProduct() {
        return stockRepository.getStockProduct();
    }





    @GetMapping("/getCollectionCount")
    public long getCountForCollection(@RequestParam(defaultValue = "") String collectionName) {
        return mongoTemplate.count(new Query(), collectionName);
    }


    @GetMapping("/getCountForCollectionByFarmer")
    public long getCountForCollectionByFarmer(
            @RequestParam(defaultValue = "") String collectionName,
            @RequestParam(defaultValue = "farmer") String fieldName,
            @RequestParam(defaultValue = "") String fieldValue
    ) {
        Query query = new Query(Criteria.where(fieldName).is(fieldValue));
        return mongoTemplate.count(query, collectionName);
    }






}
