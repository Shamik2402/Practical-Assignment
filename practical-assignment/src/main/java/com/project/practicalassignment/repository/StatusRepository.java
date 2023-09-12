package com.project.practicalassignment.repository;

import com.project.practicalassignment.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}
