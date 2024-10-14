package org.pgm.todopro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.pgm.todopro.dto.TodoDTO;
import org.pgm.todopro.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    //@Autowired
    private final TodoService todoService;
    @GetMapping("/register")
    public void registerGet(){
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("registerPost()" + todoDTO);
        if(bindingResult.hasErrors()) {
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    @GetMapping("/list")
    public void list(Model model){
        log.info("list"); // todo/list
        List<TodoDTO> todoList=todoService.getAll();
        model.addAttribute("todoList", todoList);
    }
    @GetMapping("/read")
    public void read(@RequestParam("tno") int tno, Model model){
        log.info("read");
        TodoDTO todoDTO=todoService.getOne(tno);
        model.addAttribute("dto", todoDTO);
    }

}