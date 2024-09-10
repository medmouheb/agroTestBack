package com.agrotech.api.services.impl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class GenericEntityService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> void exportAllEntitiesToExcel(Class<T> entityClass, HttpServletResponse response) throws IOException, IllegalAccessException {
        // Retrieve all entities from the database
        List<T> entities = mongoTemplate.findAll(entityClass);

        // Create Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Entity Data");

        // Create header row dynamically based on entity fields
        Row headerRow = sheet.createRow(0);
        Field[] fields = entityClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);  // Make private fields accessible
            headerRow.createCell(i).setCellValue(fields[i].getName());
        }

        // Populate the sheet with entity data
        int rowIndex = 1;
        for (T entity : entities) {
            Row row = sheet.createRow(rowIndex++);
            for (int i = 0; i < fields.length; i++) {
                Object value = fields[i].get(entity);  // Get field value dynamically
                row.createCell(i).setCellValue(value != null ? value.toString() : "");  // Convert to string, handle nulls
            }
        }

        // Write the data to the response output stream
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=entity_data.xlsx");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
        }
    }
}
