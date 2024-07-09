package com.agrotech.api.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StockRepositoryCustomImpl implements StockRepositoryCustom{


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Map> getStockWarehouse(){
        GroupOperation groupByDate = Aggregation.group("warehouse").count().as("warehouse");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project()
                        .andExpression("warehouse").as("warehouse"),
                groupByDate
        );

        return mongoTemplate.aggregate(aggregation, "stock", Map.class).getMappedResults();

    }

    @Override
    public List<Map> getStockProduct(){
        GroupOperation groupByDate = Aggregation.group("product").count().as("product");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project()
                        .andExpression("product").as("product"),
                groupByDate
        );

        return mongoTemplate.aggregate(aggregation, "stock", Map.class).getMappedResults();

    }
}
