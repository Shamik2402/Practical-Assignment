package com.project.practicalassignment.repository;

import com.project.practicalassignment.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
