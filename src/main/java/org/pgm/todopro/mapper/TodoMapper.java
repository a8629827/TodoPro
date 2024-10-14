package org.pgm.todopro.mapper;

import org.pgm.todopro.dto.TodoDTO;
import org.pgm.todopro.vo.TodoVO;

import java.util.List;

public interface TodoMapper {
    String getTime(); //추상 메소드 생성
    void insert(TodoVO todoVO);
    List<TodoVO> getList();
    TodoVO selectOne(int tno);
}
