package com.gyana.Gyana_Social.service;

import com.gyana.Gyana_Social.models.Story;

import java.util.List;

public interface StoryService {

    Story createStory(Story story, Integer userId) throws Exception;

    String deleteStory(Integer storyId, Integer userId) throws Exception;

    List<Story> findStoryByUserId(Integer userId);

    Story findStoryById(Integer storyId) throws Exception;

    List<Story> findAllActiveStories();

    List<Story> findStoriesByFollowing(Integer userId) throws Exception;
}
