package com.gentlemans.toDo.controller;

import com.gentlemans.toDo.model.ToDoModel;
import com.gentlemans.toDo.repository.ToDoRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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
    public ResponseEntity<ToDoModel> getNoteById(@PathVariable(value = "id")Integer noteId)
        throws ResourceNotFoundException {
        ToDoModel toDoModel =
                toDoRepository
                .findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada no ::" + noteId));
        return ResponseEntity.ok().body(toDoModel);
    }

    @PostMapping("/notes")
    public ToDoModel createNote(@Valid @RequestBody ToDoModel toDoModel) {
        return toDoRepository.save(toDoModel);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ToDoModel> updateNote(
            @PathVariable(value = "id") Integer noteId, @Valid @RequestBody ToDoModel noteDetail)
        throws ResourceNotFoundException {

        ToDoModel toDoModel =
                toDoRepository
                .findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada no ::" + noteId));

        toDoModel.setTitle(noteDetail.getTitle());
        toDoModel.setDescription(noteDetail.getDescription());
        toDoModel.setUpdatedAt(new Date());
        final ToDoModel updateNote = toDoRepository.save(toDoModel);
        return ResponseEntity.ok(updateNote);
    }

}
