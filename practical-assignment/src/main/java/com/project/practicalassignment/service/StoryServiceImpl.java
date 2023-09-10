package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryServiceImpl implements StoryService{
    @Autowired
    private StoryRepository storyRepository;
    @Override
    public List<Story> getAllStories() {
        return this.storyRepository.findAll();
    }
}
