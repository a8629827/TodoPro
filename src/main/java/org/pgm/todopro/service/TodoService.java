package org.pgm.todopro.service;

import org.pgm.todopro.dto.TodoDTO;
import org.pgm.todopro.vo.TodoVO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    List<TodoDTO> getAll();
    TodoDTO getOne(int tno);
}

