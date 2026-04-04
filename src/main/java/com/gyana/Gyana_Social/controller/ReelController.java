package com.gyana.Gyana_Social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.gyana.Gyana_Social.Response.ApiResponse;
import com.gyana.Gyana_Social.models.Reel;
import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.service.ReelService;
import com.gyana.Gyana_Social.service.UserService;

@RestController
public class ReelController {
    @Autowired
    ReelService reelService;
    
    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/reels", consumes = "application/json")
    public ResponseEntity<Reel> createReel(@RequestHeader("Authorization") String jwt, @RequestBody Reel reel) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Reel createdReel = reelService.createNewReel(reel, reqUser.getId());
        return new ResponseEntity<>(createdReel, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/reels/{reelId}")
    public ResponseEntity<ApiResponse> deleteReel(@PathVariable Integer reelId,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = reelService.deleteReel(reelId, reqUser.getId());
        ApiResponse res = new ApiResponse(message, true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/api/reels/{reelId}")
    public ResponseEntity<Reel> findReelByIdHandler(@PathVariable Integer reelId) throws Exception {
        Reel reel = reelService.findReelById(reelId);

        return new ResponseEntity<>(reel, HttpStatus.OK);
    }

    @GetMapping("/api/reels/user/{userId}")
    public ResponseEntity<List<Reel>> findUsersReel(@PathVariable Integer userId) {
        List<Reel> reels = reelService.findReelByUserId(userId);
        return new ResponseEntity<>(reels, HttpStatus.OK);
    }

    @GetMapping("/api/reels")
    public ResponseEntity<List<Reel>> findAllReels() {
        List<Reel> reels = reelService.findAllReels();
        return new ResponseEntity<>(reels, HttpStatus.OK);
    }

    @PutMapping("/api/reels/like/{reelId}")
    public ResponseEntity<Reel> likeReelHandler(@PathVariable Integer reelId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Reel reel = reelService.likeReel(reelId, reqUser.getId());
        return new ResponseEntity<>(reel, HttpStatus.OK);
    }

    @PutMapping("/api/reels/view/{reelId}")
    public ResponseEntity<Reel> viewReelHandler(@PathVariable Integer reelId) throws Exception {
        Reel reel = reelService.incrementViewCount(reelId);
        return new ResponseEntity<>(reel, HttpStatus.OK);
    }
}
