package com.agrotech.api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="task")
public class Task extends BaseEntity {
    private String label="";
    private Boolean isDeleted=false;
    private LocalDateTime createdAt=LocalDateTime.now();

    private String tasksOwner="";

    private String actur="";

    private String listName="to do";



}
