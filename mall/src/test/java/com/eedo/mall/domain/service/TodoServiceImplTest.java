package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.TodoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class TodoServiceImplTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void testTno() throws Exception{
        //given
        Long tno = 50L;

        //when

        //then
        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO.getTitle());
    }

    @Test
    public void register() throws Exception{
        //given
        TodoDTO todoDTO = TodoDTO.builder()
                .title("new")
                .content("new")
                .complete(true)
                .localDate(LocalDate.now())
                .build();

        //when
        Long registerID = todoService.register(todoDTO);
        TodoDTO todo = todoService.get(registerID);
        //then

        assertEquals(todo.getContent(), todoDTO.getContent());


    }

    @Test
    public void modify() throws Exception{
        //given
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(1L)
                .title("update")
                .content("update")
                .complete(true)
                .localDate(LocalDate.now())
                .build();

        //when
        TodoDTO before = todoService.get(1L);
        log.info(before.getTitle());

        todoService.modify(todoDTO);
        TodoDTO modify = todoService.get(1L);

        //then
        assertEquals(todoDTO.getContent(), modify.getContent());
    }

    @Test
    public void testGetList() throws Exception{
        //given

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        //when

        //then
        todoService.getList(pageRequestDTO);
    }

}