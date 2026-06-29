package com.gyana.Gyana_Social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UIController {
    
    // ==================== ROOT & REDIRECT ====================
    
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
    
    // ==================== AUTHENTICATION ====================
    
    @GetMapping({"/login", "/login/"})
    public String login() {
        return "auth/login";
    }
    
    @GetMapping({"/signup", "/signup/", "/register", "/create-account"})
    public String signup() {
        return "auth/signup";
    }
    
    // ==================== HOME & FEED ====================
    
    @GetMapping("/ui/feed")
    public String feed(Model model) {
        model.addAttribute("title", "Feed");
        model.addAttribute("appName", "Gyana Social");
        return "feed/feed";
    }
    
    @GetMapping("/ui/explore")
    public String explore(Model model) {
        model.addAttribute("title", "Explore");
        model.addAttribute("appName", "Gyana Social");
        return "feed/explore";
    }
    
    // ==================== POSTS ====================
    
    @GetMapping("/ui/posts/create")
    public String createPostPage(Model model) {
        model.addAttribute("title", "Create Post");
        return "posts/create";
    }
    
    @GetMapping("/ui/posts/{postId}")
    public String viewPost(@PathVariable Integer postId, Model model) {
        model.addAttribute("postId", postId);
        model.addAttribute("title", "View Post");
        return "posts/detail";
    }
    
    @GetMapping("/ui/posts/saved")
    public String savedPosts(Model model) {
        model.addAttribute("title", "Saved Posts");
        return "posts/saved";
    }
    
    // ==================== STORIES ====================
    
    @GetMapping("/ui/stories/create")
    public String createStoryPage(Model model) {
        model.addAttribute("title", "Create Story");
        return "stories/create";
    }
    
    @GetMapping("/ui/stories")
    public String viewStories(Model model) {
        model.addAttribute("title", "Stories");
        return "stories/stories";
    }
    
    @GetMapping("/ui/stories/{storyId}")
    public String viewStory(@PathVariable Integer storyId, Model model) {
        model.addAttribute("storyId", storyId);
        model.addAttribute("title", "Story");
        return "stories/view";
    }
    
    // ==================== REELS ====================
    
    @GetMapping("/ui/reels")
    public String reels(Model model) {
        model.addAttribute("title", "Reels");
        return "reels/reels";
    }
    
    @GetMapping("/ui/reels/create")
    public String createReelPage(Model model) {
        model.addAttribute("title", "Create Reel");
        return "reels/create";
    }
    
    @GetMapping("/ui/reels/{reelId}")
    public String viewReel(@PathVariable Integer reelId, Model model) {
        model.addAttribute("reelId", reelId);
        model.addAttribute("title", "Reel");
        return "reels/view";
    }
    
    // ==================== PROFILES ====================
    
    @GetMapping("/ui/profile")
    public String myProfile(Model model) {
        model.addAttribute("title", "My Profile");
        return "profile/my-profile";
    }
    
    @GetMapping("/ui/profile/{userId}")
    public String userProfile(@PathVariable Integer userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("title", "User Profile");
        return "profile/user-profile";
    }
    
    @GetMapping("/ui/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("title", "Edit Profile");
        return "profile/edit";
    }
    
    // ==================== USERS ====================
    
    @GetMapping("/ui/users/search")
    public String searchUsers(Model model) {
        model.addAttribute("title", "Search Users");
        return "users/search";
    }
    
    @GetMapping("/ui/users/suggestions")
    public String userSuggestions(Model model) {
        model.addAttribute("title", "Suggested Users");
        return "users/suggestions";
    }
    
    @GetMapping("/ui/users/followers")
    public String followers(Model model) {
        model.addAttribute("title", "Followers");
        return "users/followers";
    }
    
    @GetMapping("/ui/users/following")
    public String following(Model model) {
        model.addAttribute("title", "Following");
        return "users/following";
    }
    
    // ==================== MESSAGES ====================
    
    @GetMapping("/ui/messages")
    public String messages(Model model) {
        model.addAttribute("title", "Messages");
        return "messages/messages";
    }
    
    @GetMapping("/ui/messages/{userId}")
    public String chatWithUser(@PathVariable Integer userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("title", "Chat");
        return "messages/chat";
    }
    
    // ==================== SETTINGS ====================
    
    @GetMapping("/ui/settings")
    public String settings(Model model) {
        model.addAttribute("title", "Settings");
        return "settings/settings";
    }
    
    @GetMapping("/ui/account-settings")
    public String accountSettings(Model model) {
        model.addAttribute("title", "Account Settings");
        return "settings/account";
    }
    
    // ==================== DASHBOARD ====================
    
    @GetMapping("/ui/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }
}
