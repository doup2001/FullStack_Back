package com.eedo.mall.domain.repository.search;

import com.eedo.mall.domain.entity.QTodo;
import com.eedo.mall.domain.entity.Todo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {
        log.info("Search1...");
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        query.where(todo.title.contains("1"));
        query.fetch(); // fetch하면 실행가능

        //페이징 처리가 달라졌다
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        this.getQuerydsl().applyPagination(pageable, query);


        return null;

    }



}
