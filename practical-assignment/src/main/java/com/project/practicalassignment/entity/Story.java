package com.project.practicalassignment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Data
@Builder
@Getter
@Table(name = "Story")
public class Story {
    @Id
    @Column(name = "Story_Id")
    private long storyId;
    @Column(name = "Title")
    private String storyTitle;
    @Column(name = "Description")
    private String storyDescription;
    @OneToOne
    @JoinColumn(name = "Status_Id", referencedColumnName = "Status_Id")
    private Status storyStatus;
    @OneToOne
    @JoinColumn(name = "Priority_Id", referencedColumnName = "Priority_Id")
    private Priority storyPriority;
    @OneToOne
    @JoinColumn(name = "Story_Type_Id", referencedColumnName = "Story_Type_Id")
    private StoryType storyType;
    @Column(name = "Created_On")
    private LocalDate createdDate;
}