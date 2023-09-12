package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{

    private final StoryRepository repository;
    @Override
    public List<Story> getAllStories() {
        return this.repository.findAll();
    }

    @Override
    public Story deleteStoryById(long id) {

        Story story = repository.findById(id).get();
        repository.delete(story);
        return story;
    }
}
