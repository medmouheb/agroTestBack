package com.agrotech.api.controller;
import com.agrotech.api.services.impl.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.ClassNotFoundException;



@CrossOrigin(origins = {"*"}, maxAge = 3600)

@RestController
@RequestMapping("/excel")
public class GenericEntityController {
    @Autowired
    private GenericEntityService genericEntityService;

    @GetMapping("/export/excel")
    public HttpServletResponse exportEntitiesToExcel(@RequestParam String entityName, HttpServletResponse response) throws IOException, ClassNotFoundException, IllegalAccessException {
        // Dynamically get the entity class by its name
        Class<?> entityClass = Class.forName("com.example.project.model." + entityName);

        // Export all entities to an Excel file
        genericEntityService.exportAllEntitiesToExcel(entityClass, response);
        return response;
    }
}
