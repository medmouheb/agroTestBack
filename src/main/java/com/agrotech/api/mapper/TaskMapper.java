package com.agrotech.api.mapper;


import com.agrotech.api.dto.TaskDto;
import com.agrotech.api.model.Task;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TaskMapper  extends BaseMapper<TaskDto, Task> {
}
