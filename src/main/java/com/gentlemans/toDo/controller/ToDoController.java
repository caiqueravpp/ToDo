package com.gentlemans.toDo.controller;

import com.gentlemans.toDo.model.ToDoModel;
import com.gentlemans.toDo.repository.ToDoRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/toDo")
public class ToDoController {
    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/notes")
    public List<ToDoModel> getAllnotes(){
        return toDoRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<ToDoModel> getNotesById(@PathVariable(value = "id")Integer noteId)
        throws ResourceNotFoundException {
        ToDoModel toDoModel =
                toDoRepository
                .findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada no ::") + noteId);
        return ResponseEntity.ok().body(toDoModel);
    }

}
