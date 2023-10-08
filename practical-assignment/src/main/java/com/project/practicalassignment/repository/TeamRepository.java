package com.project.practicalassignment.repository;

import com.project.practicalassignment.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
