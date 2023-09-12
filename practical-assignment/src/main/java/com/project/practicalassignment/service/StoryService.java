package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Story;

import java.util.List;

public interface StoryService {

    public List<Story> getAllStories();

    public Story deleteStoryById(long id);
}
