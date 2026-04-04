package com.gyana.Gyana_Social.service;

import java.util.List;

import com.gyana.Gyana_Social.models.Reel;

public interface ReelService {

    Reel createNewReel(Reel reel, Integer userId) throws Exception;

    String deleteReel(Integer reelId, Integer userId) throws Exception;

    List<Reel> findReelByUserId(Integer userId);

    Reel findReelById(Integer reelId) throws Exception;

    List<Reel> findAllReels();

    Reel likeReel(Integer reelId, Integer userId) throws Exception;

    Reel incrementViewCount(Integer reelId) throws Exception;
}
