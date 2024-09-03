package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.TodoDTO;
import com.eedo.mall.domain.entity.Todo;
import com.eedo.mall.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public TodoDTO get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        return entityToDTO(todo);
    }

    @Override
    public Long register(TodoDTO dto) {

        Todo result = dtoToEntity(dto);

        Todo todo = todoRepository.save(result);
        return todo.getTno();
    }

    @Override
    public void modify(TodoDTO dto) {

        Optional<Todo> result = todoRepository.findById(dto.getTno());
        Todo todo = result.orElseThrow();

        todo.changeTitle(dto.getTitle());
        todo.changeContent(dto.getContent());
        todo.changeLocalDate(dto.getLocalDate());

        todoRepository.save(todo);

    }

    @Override
    public void remove(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        todoRepository.delete(todo);

    }

    @Override
    public TodoDTO entityToDTO(Todo todo) {
        return TodoService.super.entityToDTO(todo);
    }

    @Override
    public Todo dtoToEntity(TodoDTO todoDTO) {
        return TodoService.super.dtoToEntity(todoDTO);
    }


    //페이징 처리

}
