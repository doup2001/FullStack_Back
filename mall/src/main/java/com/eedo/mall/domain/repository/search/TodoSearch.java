package com.eedo.mall.domain.repository.search;

import com.eedo.mall.domain.entity.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1();

}
