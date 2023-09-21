package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Priority;
import com.project.practicalassignment.entity.Status;
import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.entity.StoryType;
import com.project.practicalassignment.repository.PriorityRepository;
import com.project.practicalassignment.repository.StatusRepository;
import com.project.practicalassignment.repository.StoryRepository;
import com.project.practicalassignment.repository.StoryTypeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {

    @Mock
    private StoryRepository storyRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private PriorityRepository priorityRepository;

    @Mock
    private StoryTypeRepository storyTypeRepository;

    @InjectMocks
    private StoryServiceImpl storyService;

    private List<Story> storyList;

    private Story story;

    @BeforeEach
    public void setUp() {
        // Initialize the test data
        storyList = new ArrayList<>();

        Status status = Status.builder()
                .statusName("To-Do").build();


        Priority priority = Priority.builder()
                .priorityLevel("High").build();

        StoryType storyType = StoryType.builder()
                .storyType("Bug").build();

        story = Story.builder()
                .storyId(1L)
                .title("This is a title")
                .description("This is a description")
                .status(status)
                .priority(priority)
                .type(storyType)
                .createdDate(LocalDate.now())
                .build();
        storyList.add(story);
    }

    @Test
    public void testGetAllStories() {
        // Stub the behavior of storyRepository.findAll() to return the test data
        when(storyRepository.findAll()).thenReturn(storyList);

        // Call the service method to get all stories
        List<Story> result = storyService.getAllStories();

        // Assert that the result matches the test data
        Assertions.assertThat(result).isEqualTo(storyList);

        Assertions.assertThat(result.size()).isGreaterThan(0);
    }
    @Test
    public void testDeleteStoryById() {

        long id = 1L;
        storyRepository.save(story);
        // Mock the findById method to return the story
        when(storyRepository.findById(id)).thenReturn(Optional.of(story));

        // Perform the delete operation
        Story deletedStory = storyService.deleteStoryById(id);
        storyRepository.deleteById(id);
        // Verify that deleteById was called
        verify(storyRepository, times(1)).deleteById(id);

        // Verify that the deletedStory matches the original story
        assertEquals(story.getStoryId(), deletedStory.getStoryId());
    }
    @Test
    public void testCreateStory() {

        Status status1 = Status.builder().statusId(1).statusName("To-Do").build();
        Priority priority1 = Priority.builder().priorityId(2).priorityLevel("Medium").build();
        StoryType storyType1 = StoryType.builder().storyTypeId(3).storyType("Enhancement").build();

        Status status = Status.builder()
                .statusId(1).build();


        Priority priority = Priority.builder()
                .priorityId(2).build();

        StoryType storyType = StoryType.builder()
                .storyTypeId(3).build();

        story = Story.builder()
                .storyId(1L)
                .title("This is a title")
                .description("This is a description")
                .status(status)
                .priority(priority)
                .type(storyType)
                .createdDate(LocalDate.now())
                .build();

        when(storyRepository.save(story)).thenReturn(story);

        when(statusRepository.findById(status.getStatusId())).thenReturn(Optional.of(status1));
        when(priorityRepository.findById(priority.getPriorityId())).thenReturn(Optional.of(priority1));
        when(storyTypeRepository.findById(storyType.getStoryTypeId())).thenReturn(Optional.of(storyType1));

        Story createdStory = storyService.createNewStory(story);

        assertEquals(createdStory,story);
    }
    @Test
    public void testUpdateStoryById() {

        Status statusToUpdate = Status.builder().statusId(1).statusName("To-Do").build();
        Priority priorityToUpdate = Priority.builder().priorityId(2).priorityLevel("Medium").build();
        StoryType storyTypeToUpdate = StoryType.builder().storyTypeId(3).storyType("Enhancement").build();

        Status status = Status.builder()
                .statusId(1).build();


        Priority priority = Priority.builder()
                .priorityId(2).build();

        StoryType storyType = StoryType.builder()
                .storyTypeId(3).build();

        Story storyToUpdate = Story.builder()
                .storyId(1L)
                .title("This is a title")
                .description("This is a description")
                .status(status)
                .priority(priority)
                .type(storyType)
                .createdDate(LocalDate.now())
                .build();

        when(storyRepository.findById(1L)).thenReturn(Optional.of(story));

        when(statusRepository.findById(status.getStatusId())).thenReturn(Optional.of(statusToUpdate));
        when(priorityRepository.findById(priority.getPriorityId())).thenReturn(Optional.of(priorityToUpdate));
        when(storyTypeRepository.findById(storyType.getStoryTypeId())).thenReturn(Optional.of(storyTypeToUpdate));

        when(storyRepository.save(any(Story.class))).thenReturn(storyToUpdate);

        storyService.updateStoryById(storyToUpdate,1L);

        assertEquals(story.getPriority().getPriorityLevel(), "Medium");
    }
    @Test
    public void testGetStoryById() {

        when(storyRepository.findById(1L)).thenReturn(Optional.of(story));

        Story specificStory = storyService.getStoryById(1L);
        assertEquals(specificStory,story);
    }
}
