package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.TaskRepository;
import com.agrotech.api.dto.TaskDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.TaskMapper;
import com.agrotech.api.model.Task;
import com.agrotech.api.services.TaskService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository ;

    @Autowired
    private TaskMapper taskMapper ;

    public Task save(Task dto) {

        return taskRepository.save(dto);

    }

    @Override
    public TaskDto create(TaskDto dto) throws DocumentException, FileNotFoundException {
        return taskMapper.toDto(save(taskMapper.toEntity(dto))) ;
    }

    @Override
    public TaskDto update(String s, TaskDto dto) throws NotFoundException {
        Optional<Task> camOptional =  taskRepository.findById(s);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Task not found ");
        }

        Task campanyExisting = camOptional.get();
        taskMapper.partialUpdate(campanyExisting, dto);

        return taskMapper.toDto(save(campanyExisting));
    }

    @Override
    public TaskDto findById(String s) throws NotFoundException {
        Optional<Task> campOptional = taskRepository.findById(s);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Task not found ");
        }
        return taskMapper.toDto(campOptional.get());
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List findAllByDeletee(boolean isdeleted) {
        return taskRepository.findByIsDeleted(isdeleted);
    }

    @Override
    public Page<TaskDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String s) throws NotFoundException {
        if(!taskRepository.existsById(s)) {
            throw new NotFoundException("task not found ");
        }

        taskRepository.deleteById(s);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Task> groOptional =  taskRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Task not found ");
        }

        Task groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        taskRepository.save(groExisting);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Task> groOptional =  taskRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Task not found ");
        }

        Task groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        taskRepository.save(groExisting);
    }

    @Override
    public List<Task> findAllByOwnerActive(String owner) {
        return  taskRepository.findByTasksOwnerContainingIgnoreCaseAndIsDeleted(owner,true);
    }

    @Override
    public List<Task> findAllByOwnerAndActurActive(String owner, String actur) {
        return taskRepository.findByTasksOwnerContainingIgnoreCaseAndActurContainingIgnoreCaseAndIsDeleted(owner,actur,true);
    }

    @Override
    public List<Task> findAllByOwnerDisactive(String owner) {
        return  taskRepository.findByTasksOwnerContainingIgnoreCaseAndIsDeleted(owner,false);
    }

    @Override
    public List<Task> findAllByOwnerAndActurDisactive(String owner, String actur) {
        return taskRepository.findByTasksOwnerContainingIgnoreCaseAndActurContainingIgnoreCaseAndIsDeleted(owner,actur,false);
    }
}
