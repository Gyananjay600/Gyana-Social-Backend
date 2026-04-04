package com.gyana.Gyana_Social.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gyana.Gyana_Social.models.User;
import com.gyana.Gyana_Social.repository.CommentRepository;
import com.gyana.Gyana_Social.repository.PostRepository;
import com.gyana.Gyana_Social.repository.ReelRepository;
import com.gyana.Gyana_Social.repository.UserRepository;
import com.gyana.Gyana_Social.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    
    @Autowired
    PostRepository postRepository;
    
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ReelRepository reelRepository;
    
    @PersistenceContext
    private EntityManager entityManager;



    @GetMapping("/api/users")
    public List<User> getUsers() {

        List<User>users=userRepository.findAll(); // data retrieve from the database by findAll()

        return users;

    }

    @GetMapping("/api/users/{userid}")
    public User getUsersById(@PathVariable("userid") Integer id) throws Exception {

        User user = userService.findUserById(id);

        return user;


    }



    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        User updatedUser = userService.updateUser(user, reqUser.getId());

        return updatedUser;


    }

    @PutMapping("/api/users/follow/{userid2}")
    public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userid2) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(),userid2);

        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User>users=userService.searchUser(query);
        return users;
    }
    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization")String jwt){

        User user = userService.findUserByJwt(jwt);

        user.setPassword(null);
        return user;
    }


    @DeleteMapping("/api/users/{userid}")
    @Transactional
    public String deleteUser(@PathVariable("userid") Integer userid, @RequestHeader("Authorization") String jwt) throws Exception {

        // Verify the requesting user has permission to delete this account
        User reqUser = userService.findUserByJwt(jwt);
        if (!reqUser.getId().equals(userid)) {
            throw new Exception("You can only delete your own account");
        }

        Optional<User> user = userRepository.findById(userid);

        if (user.isEmpty()){
            throw new Exception("User not exist with this id :"+ userid);
        }

        User userToDelete = user.get();
        
        // Step 1: Remove this user's saved posts from all other users' savedPost lists
        List<User> allUsers = userRepository.findAll();
        for (User u : allUsers) {
            u.getSavedPost().removeIf(post -> post.getUser().getId().equals(userid));
            userRepository.save(u);
        }
        
        // Step 2: Clear the saved posts list for this user
        userToDelete.getSavedPost().clear();
        userRepository.save(userToDelete);
        entityManager.flush();
        
        // Step 3: Delete all comments by this user first
        commentRepository.deleteByUserId(userid);
        entityManager.flush();
        
        // Step 4: Delete all reels by this user
        reelRepository.deleteByUserId(userid);
        entityManager.flush();
        
        // Step 5: Delete all posts by this user (this will also cascade delete related comments)
        postRepository.deleteByUserId(userid);
        entityManager.flush();
        
        // Step 6: Finally delete the user
        userRepository.delete(userToDelete);
        entityManager.flush();

        return "User Deleted Successfully with id !!!" + " " + userid;
    }
}
