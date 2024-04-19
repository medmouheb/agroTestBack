package com.agrotech.api.services;
import com.agrotech.api.exceptions.NotFoundException;

import com.agrotech.api.dto.TaskDto;
import com.agrotech.api.model.Task;

import java.util.List;

public interface TaskService extends BaseService<TaskDto,String> {
    public void setNotArchive(String id) throws NotFoundException;
    public void archive(String id) throws NotFoundException;

    List<Task> findAllByOwnerActive(String owner);
    List<Task> findAllByOwnerAndActurActive(String owner,String actur);

    List<Task> findAllByOwnerDisactive(String owner);
    List<Task> findAllByOwnerAndActurDisactive(String owner,String actur);

}
