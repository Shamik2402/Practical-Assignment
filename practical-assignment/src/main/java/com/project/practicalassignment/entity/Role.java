package com.project.practicalassignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name = "Role")
    private String role;
}
