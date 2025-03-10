package org.pgm.todopro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.pgm.todopro.dto.PageRequestDTO;
import org.pgm.todopro.dto.PageResponseDTO;
import org.pgm.todopro.dto.TodoDTO;
import org.pgm.todopro.mapper.TodoMapper;
import org.pgm.todopro.vo.TodoVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info("service register" + todoDTO);
        TodoVO todoVO=modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
    }
    public List<TodoDTO> getAll(){
        log.info("service getAll");
        /*return todoMapper.getList();*/
        List<TodoDTO> dtoList=todoMapper.getList().stream()
                .map(vo->modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toUnmodifiableList());
                return dtoList;
    }

    @Override
    public TodoDTO getOne(int tno) {
        log.info("service getOne");
        TodoVO todoVO=todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    @Override
    public void remove(Long tno) {
        log.info("service remove");
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO=modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList=todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo->modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toUnmodifiableList());
        int total = todoMapper.getCount(pageRequestDTO);
        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }
}
