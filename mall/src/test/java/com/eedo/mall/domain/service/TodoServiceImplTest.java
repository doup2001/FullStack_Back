package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.TodoDTO;
import com.eedo.mall.domain.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


}