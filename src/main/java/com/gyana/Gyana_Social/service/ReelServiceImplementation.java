package com.gyana.Gyana_Social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyana.Gyana_Social.models.Reel;
import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.repository.ReelRepository;
import com.gyana.Gyana_Social.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class ReelServiceImplementation implements ReelService {

    @Autowired
    ReelRepository reelRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @Override
    public Reel createNewReel(Reel reel, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Reel newReel = new Reel();
        newReel.setTitle(reel.getTitle());
        newReel.setDescription(reel.getDescription());
        newReel.setVideoUrl(reel.getVideoUrl());
        newReel.setThumbnail(reel.getThumbnail());
        newReel.setCreatedAt(LocalDateTime.now());
        newReel.setUser(user);
        newReel.setLikeCount(0);
        newReel.setViewCount(0);

        return reelRepository.save(newReel);
    }

    @Override
    public String deleteReel(Integer reelId, Integer userId) throws Exception {
        Reel reel = findReelById(reelId);
        User user = userService.findUserById(userId);

        if (!reel.getUser().getId().equals(user.getId())) {
            throw new Exception("You can't delete another user's reel !!!");
        }

        reelRepository.delete(reel);
        return "reel deleted successfully";
    }

    @Override
    public List<Reel> findReelByUserId(Integer userId) {
        return reelRepository.findReelByUserId(userId);
    }

    @Override
    public Reel findReelById(Integer reelId) throws Exception {
        Optional<Reel> opt = reelRepository.findById(reelId);
        if (opt.isEmpty()) {
            throw new Exception("reel not found with id " + reelId);
        }

        return opt.get();
    }

    @Override
    public List<Reel> findAllReels() {
        return reelRepository.findAll();
    }

    @Override
    @Transactional
    public Reel likeReel(Integer reelId, Integer userId) throws Exception {
        Reel reel = findReelById(reelId);
        User user = userService.findUserById(userId);

        if (reel.getLiked().contains(user)) {
            reel.getLiked().remove(user);
            reel.setLikeCount(reel.getLikeCount() - 1);
        } else {
            reel.getLiked().add(user);
            reel.setLikeCount(reel.getLikeCount() + 1);
        }

        return reelRepository.save(reel);
    }

    @Override
    @Transactional
    public Reel incrementViewCount(Integer reelId) throws Exception {
        Reel reel = findReelById(reelId);
        reel.setViewCount(reel.getViewCount() + 1);
        return reelRepository.save(reel);
    }
}
