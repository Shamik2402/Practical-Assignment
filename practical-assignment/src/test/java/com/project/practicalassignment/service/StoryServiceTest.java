package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Priority;
import com.project.practicalassignment.entity.Status;
import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.entity.StoryType;
import com.project.practicalassignment.repository.StoryRepository;
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

@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {

    @Mock
    private StoryRepository storyRepository;

    @InjectMocks
    private StoryServiceImpl storyService;

    private List<Story> storyList;

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

        Story story = Story.builder()
                .storyTitle("This is a title")
                .storyDescription("This is a description")
                .storyStatus(status)
                .storyPriority(priority)
                .storyType(storyType)
                .createdDate(LocalDate.now())
                .build();

        storyList.add(story);
    }

    @Test
    public void testGetAllStories() {
        // Stub the behavior of storyRepository.findAll() to return the test data
        Mockito.when(storyRepository.findAll()).thenReturn(storyList);

        // Call the service method to get all stories
        List<Story> result = storyService.getAllStories();

        // Assert that the result matches the test data
        Assertions.assertThat(result).isEqualTo(storyList);

        Assertions.assertThat(result.size()).isGreaterThan(0);
    }
}
