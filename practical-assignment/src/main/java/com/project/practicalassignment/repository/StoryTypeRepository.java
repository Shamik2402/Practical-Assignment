package com.project.practicalassignment.repository;

import com.project.practicalassignment.entity.StoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryTypeRepository extends JpaRepository<StoryType,Integer> {
}
