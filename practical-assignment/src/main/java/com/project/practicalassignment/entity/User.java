package com.project.practicalassignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "User")
public class User {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @OneToOne
    @JoinColumn(name = "Role", referencedColumnName = "Id")
    private Role role;
    @Column(name = "Reports_To")
    private String reportsTo;
    @OneToOne
    @JoinColumn(name = "Team", referencedColumnName = "Id")
    private Team team;
}
