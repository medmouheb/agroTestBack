package com.agrotech.api.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Map;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Map> countTasksByCreatedAtDate() {
        GroupOperation groupByDate = Aggregation.group("createdAtDate").count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project()
                        .andExpression("dateToString('%Y-%m-%d', createdAt)").as("createdAtDate"),
                groupByDate
        );

        return mongoTemplate.aggregate(aggregation, "task", Map.class).getMappedResults();
    }

    public List<Map> countTasksByOwner() {
        GroupOperation groupByOwner = Aggregation.group("tasksOwner").count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(
                groupByOwner
        );

        return mongoTemplate.aggregate(aggregation, "task", Map.class).getMappedResults();
    }

    @Override
    public List<Map> countTasksByCreatedAtDateAndOwner(String owner) {
        GroupOperation groupByDateAndOwner = Aggregation.group("createdAtDate", "owner")
                .count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("tasksOwner").is(owner)),
                Aggregation.project()
                        .andExpression("dateToString('%Y-%m-%d', createdAt)").as("createdAtDate")
                        .andExpression("tasksOwner").as("owner")
                ,
                groupByDateAndOwner
        );

        return mongoTemplate.aggregate(aggregation, "task", Map.class).getMappedResults();
    }

    @Override
    public List<Map> countTasksByActur(String owner) {
        GroupOperation groupByActur = Aggregation.group("actur").count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("tasksOwner").is(owner)),
                groupByActur
        );

        return mongoTemplate.aggregate(aggregation, "task", Map.class).getMappedResults();
    }


    @Override
    public List<Map> countTasksByActurAndList(String owner) {
        GroupOperation groupByDateAndOwner = Aggregation.group("owner", "employee","list")
                .count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("tasksOwner").is(owner)),
                Aggregation.project()
                        .andExpression("tasksOwner").as("owner")
                        .andExpression("actur").as("employee")
                        .andExpression("listName").as("list")
                ,
                groupByDateAndOwner
        );

        return mongoTemplate.aggregate(aggregation, "task", Map.class).getMappedResults();
    }
}
