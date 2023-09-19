package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Priority;
import com.project.practicalassignment.entity.Status;
import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.entity.StoryType;
import com.project.practicalassignment.repository.PriorityRepository;
import com.project.practicalassignment.repository.StatusRepository;
import com.project.practicalassignment.repository.StoryRepository;
import com.project.practicalassignment.repository.StoryTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{

    private final StoryRepository repository;
    private final StatusRepository statusRepository;
    private final PriorityRepository priorityRepository;
    private final StoryTypeRepository storyTypeRepository;
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

    @Override
    public Story createNewStory(Story story) {

        int status_id = story.getStatus().getStatusId();
        int priority_id = story.getPriority().getPriorityId();
        int status_type_id = story.getType().getStoryTypeId();

        Status status = statusRepository.findById(status_id).get();
        story.setStatus(status);

        Priority priority = priorityRepository.findById(priority_id).get();
        story.setPriority(priority);

        StoryType storyType = storyTypeRepository.findById(status_type_id).get();
        story.setType(storyType);

        Story story1 = repository.save(story);
        return story1;
    }

    @Override
    public Story updateStoryById(Story story, long id) {
        Story storyToUpdate = repository.findById(id).get();

        int status_id = story.getStatus().getStatusId();
        int priority_id = story.getPriority().getPriorityId();
        int status_type_id = story.getType().getStoryTypeId();

        Status status = statusRepository.findById(status_id).get();
        storyToUpdate.setStatus(status);

        Priority priority = priorityRepository.findById(priority_id).get();
        storyToUpdate.setPriority(priority);

        StoryType storyType = storyTypeRepository.findById(status_type_id).get();
        storyToUpdate.setType(storyType);
        storyToUpdate.setStoryId(id);
        storyToUpdate.setTitle(story.getTitle());
        storyToUpdate.setDescription(story.getDescription());
        storyToUpdate.setCreatedDate(story.getCreatedDate());
        repository.save(storyToUpdate);
        return storyToUpdate;
    }

    @Override
    public Story getStoryById(long id) {
        return this.repository.findById(id).get();
    }
}
