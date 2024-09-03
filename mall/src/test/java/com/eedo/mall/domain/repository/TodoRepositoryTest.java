package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void saveTest() {

        Todo todo = Todo.builder()
                .title("title")
                .content("content")
                .localDate(LocalDate.now())
                .complete(false)
                .build();

        Todo save = todoRepository.save(todo);

        Assertions.assertEquals(todo.getTitle(),save.getTitle());
    }

    @Test
    public void UpdateTest() {

        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
        todo.changeContent("update_content");
        todo.changeComplete(true);
        todo.changeLocalDate(LocalDate.now());

        todoRepository.save(todo);
    }

    @Test
    public void PagingTest() throws Exception{
        //given
        for (int i = 0; i < 100; i++) {
            Todo todo = Todo.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .localDate(LocalDate.now())
                    .complete(false)
                    .build();

            Todo save = todoRepository.save(todo);
        }

        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        //when
        Page<Todo> result = todoRepository.findAll(pageable);

        //then
        System.out.println("result.getTotalElements() = " + result.getTotalElements());
        System.out.println("result.getNumber() = " + result.getNumber());
        System.out.println("result.getContent() = " + result.getContent());
    }

    @Test
    public void testSearch1() throws Exception{
        //given

        todoRepository.search1();
    }
}