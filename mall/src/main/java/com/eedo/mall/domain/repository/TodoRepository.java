package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.entity.Todo;
import com.eedo.mall.domain.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long>, TodoSearch {


}
