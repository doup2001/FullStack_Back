package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.TodoDTO;
import com.eedo.mall.domain.entity.Todo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TodoService {

    TodoDTO get(Long tno);

    default TodoDTO entityToDTO(Todo todo) {

        return TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .content(todo.getContent())
                .localDate(todo.getLocalDate())
                .build();
    }


    default Todo dtoToEntity(TodoDTO todoDTO) {

        return Todo.builder()
                .tno(todoDTO.getTno())
                .title(todoDTO.getTitle())
                .content(todoDTO.getContent())
                .localDate(todoDTO.getLocalDate())
                .build();
    }

}
