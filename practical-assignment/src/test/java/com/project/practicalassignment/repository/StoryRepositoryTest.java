package com.project.practicalassignment.repository;

import com.project.practicalassignment.entity.Priority;
import com.project.practicalassignment.entity.Status;
import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.entity.StoryType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class StoryRepositoryTest {

    @Autowired
    private StoryRepository storyRepository;

    @Test
    public void StoryRepository_GetAll_ReturnsAllStory() {

        Status status = Status.builder()
                .statusName("To-Do").build();

        Priority priority = Priority.builder()
                .priorityLevel("High").build();

        StoryType storyType = StoryType.builder()
                .storyType("Bug").build();

        Story story = Story.builder()
                .title("This is a title")
                .description("This is a description")
                .status(status)
                .priority(priority)
                .type(storyType)
                .createdDate(LocalDate.now())
                .build();

        Story story1 = storyRepository.save(story);

        Assertions.assertThat(story1.getStoryId()).isGreaterThan(0);
    }
}
