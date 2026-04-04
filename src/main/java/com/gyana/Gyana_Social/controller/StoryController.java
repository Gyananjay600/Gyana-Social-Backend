package com.gyana.Gyana_Social.controller;

import java.util.List;

import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gyana.Gyana_Social.Response.ApiResponse;
import com.gyana.Gyana_Social.models.Story;
import com.gyana.Gyana_Social.service.StoryService;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    @Autowired
    StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Story> createStory(@RequestHeader("Authorization") String jwt, @RequestBody Story story) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, reqUser.getId());
        return new ResponseEntity<>(createdStory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{storyId}")
    public ResponseEntity<ApiResponse> deleteStory(@PathVariable Integer storyId,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = storyService.deleteStory(storyId, reqUser.getId());
        ApiResponse res = new ApiResponse(message, true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{storyId}")
    public ResponseEntity<Story> findStoryByIdHandler(@PathVariable Integer storyId) throws Exception {
        Story story = storyService.findStoryById(storyId);

        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Story>> findUsersStories(@PathVariable Integer userId) {
        List<Story> stories = storyService.findStoryByUserId(userId);
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    @GetMapping("/active/all")
    public ResponseEntity<List<Story>> findAllActiveStories() {
        List<Story> stories = storyService.findAllActiveStories();
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    @GetMapping("/following")
    public ResponseEntity<List<Story>> findStoriesByFollowing(@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<Story> stories = storyService.findStoriesByFollowing(reqUser.getId());
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
