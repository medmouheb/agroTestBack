package com.agrotech.api.mapper;


import com.agrotech.api.dto.TaskDto;
import com.agrotech.api.model.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper  extends BaseMapper<TaskDto, Task> {
}
