package com.eedo.mall.domain.repository.search;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.entity.QTodo;
import com.eedo.mall.domain.entity.Todo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1(PageRequestDTO pageRequestDTO) {
        log.info("Search1...");
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

//        query.where(todo.title.contains("1"));

        //페이징 처리가 달라졌다
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("tno").descending());
        this.getQuerydsl().applyPagination(pageable, query);

        List<Todo> list = query.fetch();// fetch하면 실행가능
        long total = query.fetchCount();

        return new PageImpl<>(list, pageable, total);

    }



}
