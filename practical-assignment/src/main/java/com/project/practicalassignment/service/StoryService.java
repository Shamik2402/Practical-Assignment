package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.*;

import java.util.List;
import java.util.Map;

public interface StoryService {

    public List<Story> getAllStories();

    public Story deleteStoryById(long id);

    public Story createNewStory(Story story);

    public Story updateStoryById(Story story, long id);

    public Story getStoryById(long id);

    public List<Status> getAllStatuses();

    public List<Priority> getAllPriorities();

    public List<StoryType> getAllTypes();
    public List<Story> getStoriesByTeam(String team);
}
