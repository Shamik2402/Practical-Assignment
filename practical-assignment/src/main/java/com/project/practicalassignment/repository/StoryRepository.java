package com.project.practicalassignment.repository;

import com.project.practicalassignment.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
