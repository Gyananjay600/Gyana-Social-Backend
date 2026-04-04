package com.gyana.Gyana_Social.service;

import com.gyana.Gyana_Social.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);
    public User findUserById(Integer userid) throws Exception;
    public User findUserByEmail(String email);
    public User followUser(Integer userid1, Integer userid2) throws Exception;
    public User updateUser(User user,Integer userid) throws Exception;
    public List<User> searchUser(String query);
    public User findUserByJwt(String jwt);


}
