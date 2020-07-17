package com.gentlemans.toDo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoModel {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Date createdAt;
}
