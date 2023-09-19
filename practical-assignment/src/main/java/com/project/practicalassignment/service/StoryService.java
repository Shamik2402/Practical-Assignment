package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Story;

import java.util.List;

public interface StoryService {

    public List<Story> getAllStories();

    public Story deleteStoryById(long id);

    public Story createNewStory(Story story);

    public Story updateStoryById(Story story, long id);

    public Story getStoryById(long id);
}
