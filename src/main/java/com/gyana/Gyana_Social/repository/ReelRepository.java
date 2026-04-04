package com.gyana.Gyana_Social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gyana.Gyana_Social.models.Reel;

public interface ReelRepository extends JpaRepository<Reel, Integer> {
    @Query("select r from Reel r where r.user.id =:userId")
    List<Reel> findReelByUserId(Integer userId);

    void deleteByUserId(Integer userId);
}
