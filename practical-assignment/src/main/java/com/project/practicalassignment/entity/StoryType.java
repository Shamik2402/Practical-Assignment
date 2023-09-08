package com.project.practicalassignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Story_Type")
public class StoryType {
    @Id
    @Column(name = "Story_Type_Id")
    private int storyTypeId;
    @Column(name = "Type")
    private String storyType;
}
