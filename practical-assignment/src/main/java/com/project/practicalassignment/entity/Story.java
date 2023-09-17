package com.project.practicalassignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "Story")
public class Story {
    @Id
    @Column(name = "Story_Id")
    private long storyId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @OneToOne
    @JoinColumn(name = "Status_Id", referencedColumnName = "Status_Id")
    private Status status;
    @OneToOne
    @JoinColumn(name = "Priority_Id", referencedColumnName = "Priority_Id")
    private Priority priority;
    @OneToOne
    @JoinColumn(name = "Story_Type_Id", referencedColumnName = "Story_Type_Id")
    private StoryType type;
    @Column(name = "Created_On")
    private LocalDate createdDate;
}