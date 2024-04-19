package com.agrotech.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto extends BaseDto {

    private String label="";
    private Boolean isDeleted=false;
    private LocalDateTime createdAt=LocalDateTime.now();

    private String tasksOwner="";

    private String actur="";

    private String listName="to do";
}
