package com.gentlemans.toDo.repository;

import com.gentlemans.toDo.model.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Integer> {
}
