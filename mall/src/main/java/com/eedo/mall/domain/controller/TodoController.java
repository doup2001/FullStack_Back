package com.eedo.mall.domain.controller;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.TodoDTO;
import com.eedo.mall.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO getTno(@PathVariable Long tno) {
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> getList(@ModelAttribute PageRequestDTO pageRequestDTO) {
        log.info("[MyLog]" + pageRequestDTO.getPage());

        return todoService.getList(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String,Long> register(@RequestBody TodoDTO dto) {
        Long tno = todoService.register(dto);

        return Map.of("tno", tno);
    }

}
