package com.gyana.Gyana_Social.service;

import com.gyana.Gyana_Social.config.JwtProvider;
import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {

        return null;
    }

    @Override
    public User findUserById(Integer userid) throws Exception{

        Optional<User> user = userRepository.findAllById(userid);

        if (user.isPresent()){
            return user.get();
        }

        throw new Exception("user not exist with userid : "+ userid);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;

    }

    @Override
    public User followUser(Integer reqUserId, Integer userid2) throws Exception { // userid1 wants to follow userid2

        User reqUser = findUserById(reqUserId);

        User user2 = findUserById(userid2);

        user2.getFollowers().add(reqUser.getId()); // user2's followers list += reqUser
        reqUser.getFollowings().add(user2.getId()); // reqUser's following list += user2

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;

    }

    @Override
    public User updateUser(User user,Integer userid) throws Exception {
        Optional<User> user1 = userRepository.findById(userid);

        if (user1.isEmpty()){
            throw new Exception("User not exist with this id :"+ userid);
        }

        User oldUser = user1.get();

        if (user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }

        if (user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }

        User updatedUser = userRepository.save(oldUser);

        return updatedUser;

    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return user;
    }
}
