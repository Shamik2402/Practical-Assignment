package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.*;
import com.project.practicalassignment.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoryServiceImpl implements StoryService{

    private StoryRepository repository;
    private StatusRepository statusRepository;
    private PriorityRepository priorityRepository;
    private StoryTypeRepository storyTypeRepository;
    private UserRepository userRepository;
    private TeamRepository teamRepository;
    @Override
    public List<Story> getAllStories() {
        return repository.findAll();
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
        int createdById = story.getCreatedBy().getId();
        int assignedToId = story.getAssignedTo().getId();
        int teamId = story.getTeam().getId();

        Status status = statusRepository.findById(status_id).get();
        story.setStatus(status);

        Priority priority = priorityRepository.findById(priority_id).get();
        story.setPriority(priority);

        StoryType storyType = storyTypeRepository.findById(status_type_id).get();
        story.setType(storyType);

        User createdUser = userRepository.findById(createdById).get();
        story.setCreatedBy(createdUser);

        User assignedUser = userRepository.findById(assignedToId).get();
        story.setAssignedTo(assignedUser);

        Team team = teamRepository.findById(teamId).get();
        story.setTeam(team);

        Story story1 = repository.save(story);
        return story1;
    }

    @Override
    public Story updateStoryById(Story story, long id) {
        Story storyToUpdate = repository.findById(id).get();

        int status_id = story.getStatus().getStatusId();
        int priority_id = story.getPriority().getPriorityId();
        int status_type_id = story.getType().getStoryTypeId();
        int createdById = story.getCreatedBy().getId();
        int assignedToId = story.getAssignedTo().getId();
        int teamId = story.getTeam().getId();

        Status status = statusRepository.findById(status_id).get();
        storyToUpdate.setStatus(status);

        Priority priority = priorityRepository.findById(priority_id).get();
        storyToUpdate.setPriority(priority);

        StoryType storyType = storyTypeRepository.findById(status_type_id).get();
        storyToUpdate.setType(storyType);

        User createdUser = userRepository.findById(createdById).get();
        storyToUpdate.setCreatedBy(createdUser);

        User assignedUser = userRepository.findById(assignedToId).get();
        storyToUpdate.setAssignedTo(assignedUser);

        Team team = teamRepository.findById(teamId).get();
        storyToUpdate.setTeam(team);

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

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    @Override
    public List<StoryType> getAllTypes() {
        return storyTypeRepository.findAll();
    }

    @Override
    public List<Story> getStoriesByTeam(String team) {
        return repository.findAll().stream().filter(story -> Objects.equals(story.getTeam().getName(),team)).toList();
    }
}
