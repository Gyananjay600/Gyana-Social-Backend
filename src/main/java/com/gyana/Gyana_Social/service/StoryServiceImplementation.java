package com.gyana.Gyana_Social.service;

import com.gyana.Gyana_Social.models.Story;
import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    UserService userService;


    @Override
    public Story createStory(Story story, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Story newStory = new Story();
        newStory.setImage(story.getImage());
        newStory.setCaption(story.getCaption());
        newStory.setUser(user);
        newStory.setCreatedAt(LocalDateTime.now());
        // Set story to expire in 24 hours
        newStory.setExpiresAt(LocalDateTime.now().plusHours(24));

        return storyRepository.save(newStory);
    }

    @Override
    public String deleteStory(Integer storyId, Integer userId) throws Exception {
        Story story = findStoryById(storyId);
        User user = userService.findUserById(userId);

        if (!story.getUser().getId().equals(user.getId())) {
            throw new Exception("You can't delete another user's story !!!");
        }

        storyRepository.delete(story);
        return "story deleted successfully";
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) {
        return storyRepository.findStoryByUserId(userId);
    }

    @Override
    public Story findStoryById(Integer storyId) throws Exception {
        Optional<Story> opt = storyRepository.findById(storyId);
        if (opt.isEmpty()) {
            throw new Exception("story not found with id " + storyId);
        }

        return opt.get();
    }

    @Override
    public List<Story> findAllActiveStories() {
        return storyRepository.findAllActiveStories(LocalDateTime.now());
    }

    @Override
    public List<Story> findStoriesByFollowing(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        List<Integer> followingIds = user.getFollowings();

        List<Story> stories = storyRepository.findAllActiveStories(LocalDateTime.now());

        return stories.stream()
                .filter(story -> followingIds.contains(story.getUser().getId()))
                .collect(Collectors.toList());
    }
}
