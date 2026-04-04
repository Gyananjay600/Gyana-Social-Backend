# Gyana Social - Postman Testing Guide

## Overview
This guide provides step-by-step instructions to test all API endpoints for the **Gyana Social** application using Postman.

---

## Prerequisites

1. **Postman** installed ([Download here](https://www.postman.com/downloads/))
2. **MySQL Database** running with the following configuration:
   - Host: `localhost`
   - Port: `3306`
   - Database: `gyana_social`
   - Username: `root`
   - Password: `Gyana@1234qw`
3. **Spring Boot Application** running on `http://localhost:5454`

---

## Initial Setup

### Step 1: Start the Application
1. Open terminal and navigate to the project directory
2. Run the application using:
   ```bash
   mvn spring-boot:run
   ```
   Or execute the JAR file if built
3. Verify the server is running at `http://localhost:5454`

### Step 2: Create Postman Collection
1. Open Postman
2. Click **"New"** → **"Collection"**
3. Name it: `Gyana Social API`
4. Click **"Create"**

### Step 3: Set Base URL Variable (Recommended)
1. In Postman, click **"New"** → **"Environment"**
2. Name it: `Gyana Social Dev`
3. Add variable:
   - **Variable Name:** `base_url`
   - **Initial Value:** `http://localhost:5454`
   - **Current Value:** `http://localhost:5454`
4. Click **"Save"**
5. Select this environment in the top-right dropdown

---

## API Endpoints Testing

### 1. HOME CONTROLLER ENDPOINTS

#### 1.1 Get Home Page
- **Method:** `GET`
- **URL:** `{{base_url}}/`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```
  Gyana Social is running
  ```

#### 1.2 Home Controller 2
- **Method:** `GET`
- **URL:** `{{base_url}}/home2`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```
  This is home controller 2
  ```

#### 1.3 Learning Page
- **Method:** `GET`
- **URL:** `{{base_url}}/CodeWithGyana`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```
  Gyananjay learning springboot
  ```

---

### 2. AUTHENTICATION CONTROLLER ENDPOINTS (`/auth`)

#### 2.1 User Sign Up (Register)
- **Method:** `POST`
- **URL:** `{{base_url}}/auth/signup`
- **Headers:** 
  ```
  Content-Type: application/json
  ```
- **Body (raw JSON):**
  ```json
  {
    "id": 1,
    "email": "user1@example.com",
    "password": "password@123",
    "firstName": "John",
    "lastName": "Doe"
  }
  ```
- **Expected Response (200 OK):**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "message": "Register success"
  }
  ```
- **Notes:**
  - Email must be unique
  - Save the returned token for subsequent requests
  - Password will be encoded before storing

#### 2.2 User Sign In (Login)
- **Method:** `POST`
- **URL:** `{{base_url}}/auth/signin`
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (raw JSON):**
  ```json
  {
    "email": "user1@example.com",
    "password": "password@123"
  }
  ```
- **Expected Response (200 OK):**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "message": "Register success"
  }
  ```
- **Notes:**
  - JWT token is valid for 10 days
  - Use this token for subsequent authenticated requests

---

### 3. USER CONTROLLER ENDPOINTS

#### 3.1 Get All Users
- **Method:** `GET`
- **URL:** `{{base_url}}/api/users`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "email": "user1@example.com",
      "firstName": "John",
      "lastName": "Doe",
      "password": "encoded_password"
    }
  ]
  ```

#### 3.2 Get User By ID
- **Method:** `GET`
- **URL:** `{{base_url}}/api/users/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  {
    "id": 1,
    "email": "user1@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "password": "encoded_password"
  }
  ```
- **Notes:**
  - Replace `1` with the actual user ID
  - Will throw exception if user not found

#### 3.3 Update User
- **Method:** `PUT`
- **URL:** `{{base_url}}/api/users/1`
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (raw JSON):**
  ```json
  {
    "id": 1,
    "email": "user1_updated@example.com",
    "password": "newpassword@123",
    "firstName": "Jane",
    "lastName": "Smith"
  }
  ```
- **Expected Response (200 OK):**
  ```json
  {
    "id": 1,
    "email": "user1_updated@example.com",
    "firstName": "Jane",
    "lastName": "Smith",
    "password": "encoded_password"
  }
  ```

#### 3.4 Delete User
- **Method:** `DELETE`
- **URL:** `{{base_url}}/api/users/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):** (Success message)

---

### 4. POST CONTROLLER ENDPOINTS

#### 4.1 Create Post
- **Method:** `POST`
- **URL:** `{{base_url}}/api/posts/user/1`
- **Headers:**
  ```
  Content-Type: application/json
  Authorization: Bearer <JWT_TOKEN>
  ```
- **Body (raw JSON):**
  ```json
  {
    "content": "This is my first post",
    "image": "image_url_here"
  }
  ```
- **Expected Response (202 ACCEPTED):**
  ```json
  {
    "id": 1,
    "content": "This is my first post",
    "image": "image_url_here",
    "user": {
      "id": 1,
      "email": "user1@example.com"
    },
    "createdAt": "2026-03-17T10:00:00"
  }
  ```
- **Notes:**
  - Replace `1` with the actual user ID
  - JWT token obtained from login is recommended

#### 4.2 Get All Posts
- **Method:** `GET`
- **URL:** `{{base_url}}/api/posts`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "content": "This is my first post",
      "image": "image_url_here",
      "likes": 0,
      "saved": false
    }
  ]
  ```

#### 4.3 Get Post By ID
- **Method:** `GET`
- **URL:** `{{base_url}}/api/posts/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  {
    "id": 1,
    "content": "This is my first post",
    "image": "image_url_here",
    "likes": 0,
    "saved": false
  }
  ```
- **Notes:** Replace `1` with the actual post ID

#### 4.4 Get User's Posts
- **Method:** `GET`
- **URL:** `{{base_url}}/api/posts/user/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "content": "This is my first post",
      "user": {"id": 1}
    }
  ]
  ```
- **Notes:** Replace `1` with the actual user ID

#### 4.5 Like Post
- **Method:** `PUT`
- **URL:** `{{base_url}}/api/posts/like/1/user/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (202 ACCEPTED):**
  ```json
  {
    "id": 1,
    "content": "This is my first post",
    "likes": 1
  }
  ```
- **Notes:**
  - First parameter is Post ID
  - Second parameter is User ID
  - Likes count increments on each call

#### 4.6 Save Post
- **Method:** `PUT`
- **URL:** `{{base_url}}/api/posts/save/1/user/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (202 ACCEPTED):**
  ```json
  {
    "id": 1,
    "content": "This is my first post",
    "saved": true
  }
  ```
- **Notes:**
  - First parameter is Post ID
  - Second parameter is User ID

#### 4.7 Delete Post
- **Method:** `DELETE`
- **URL:** `{{base_url}}/api/posts/1/user/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  {
    "message": "Post deleted successfully",
    "status": true
  }
  ```
- **Notes:**
  - First parameter is Post ID
  - Second parameter is User ID (for authorization)

---

### 5. COMMENT CONTROLLER ENDPOINTS

#### 5.1 Create Comment
- **Method:** `POST`
- **URL:** `{{base_url}}/api/comments/post/1/user/1`
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (raw JSON):**
  ```json
  {
    "content": "Great post!"
  }
  ```
- **Expected Response (201 CREATED):**
  ```json
  {
    "id": 1,
    "content": "Great post!",
    "post": {"id": 1},
    "user": {"id": 1},
    "createdAt": "2026-03-17T10:05:00"
  }
  ```
- **Notes:**
  - First parameter is Post ID
  - Second parameter is User ID

#### 5.2 Get All Comments
- **Method:** `GET`
- **URL:** `{{base_url}}/api/comments`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "content": "Great post!"
    }
  ]
  ```

#### 5.3 Get Comment By ID
- **Method:** `GET`
- **URL:** `{{base_url}}/api/comments/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  {
    "id": 1,
    "content": "Great post!",
    "user": {"id": 1},
    "post": {"id": 1}
  }
  ```
- **Notes:** Replace `1` with the actual comment ID

#### 5.4 Get Comments By Post ID
- **Method:** `GET`
- **URL:** `{{base_url}}/api/comments/post/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "content": "Great post!",
      "post": {"id": 1}
    }
  ]
  ```
- **Notes:** Replace `1` with the actual post ID

#### 5.5 Get Comments By User ID
- **Method:** `GET`
- **URL:** `{{base_url}}/api/comments/user/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "content": "Great post!",
      "user": {"id": 1}
    }
  ]
  ```
- **Notes:** Replace `1` with the actual user ID

#### 5.6 Update Comment
- **Method:** `PUT`
- **URL:** `{{base_url}}/api/comments/1/user/1`
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (raw JSON):**
  ```json
  {
    "content": "Updated comment text"
  }
  ```
- **Expected Response (200 OK):**
  ```json
  {
    "id": 1,
    "content": "Updated comment text",
    "user": {"id": 1}
  }
  ```
- **Notes:**
  - First parameter is Comment ID
  - Second parameter is User ID (for authorization)

#### 5.7 Delete Comment
- **Method:** `DELETE`
- **URL:** `{{base_url}}/api/comments/1/user/1`
- **Headers:** None required
- **Body:** None
- **Expected Response (200 OK):**
  ```json
  {
    "message": "Comment deleted successfully",
    "status": true
  }
  ```
- **Notes:**
  - First parameter is Comment ID
  - Second parameter is User ID (for authorization)

---

## Testing Workflow

### Recommended Testing Order:

1. **Start with Home Endpoints** (verify API is running)
   - Test: Get Home Page

2. **Test Authentication**
   - Create new users with Sign Up
   - Login with Sign In
   - Save JWT tokens for authenticated requests

3. **Test User Management**
   - Get all users
   - Get specific user
   - Update user
   - Delete user

4. **Test Posts**
   - Create multiple posts
   - Get all posts
   - Get specific post
   - Like and save posts
   - Delete posts

5. **Test Comments**
   - Create comments on posts
   - Get all comments
   - Update comments
   - Delete comments

---

## Example Complete Workflow

### Step 1: User Registration
```
POST http://localhost:5454/auth/signup
Content-Type: application/json

{
  "id": 1,
  "email": "testuser@gmail.com",
  "password": "Test@1234",
  "firstName": "Test",
  "lastName": "User"
}
```
✓ Save the returned JWT token

### Step 2: Create a Post
```
POST http://localhost:5454/api/posts/user/1
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>

{
  "content": "My First Post",
  "image": "https://example.com/image.jpg"
}
```
✓ Note the returned Post ID

### Step 3: Create a Comment
```
POST http://localhost:5454/api/comments/post/1/user/1
Content-Type: application/json

{
  "content": "Nice post!"
}
```

### Step 4: Like the Post
```
PUT http://localhost:5454/api/posts/like/1/user/1
```

### Step 5: Verify All Data
```
GET http://localhost:5454/api/posts/1
GET http://localhost:5454/api/comments/post/1
GET http://localhost:5454/api/users/1
```

---

## Error Handling

Common HTTP Status Codes:

| Status Code | Meaning |
|------------|---------|
| 200 | OK - Request successful |
| 201 | CREATED - Resource created successfully |
| 202 | ACCEPTED - Request accepted |
| 400 | BAD REQUEST - Invalid request body/parameters |
| 401 | UNAUTHORIZED - Missing/invalid JWT token |
| 403 | FORBIDDEN - Access denied |
| 404 | NOT FOUND - Resource not found |
| 500 | INTERNAL SERVER ERROR - Server error |

---

## Troubleshooting

### Issue: Connection Refused
- **Solution:** Ensure Spring Boot application is running on port 5454

### Issue: Database Connection Error
- **Solution:** Verify MySQL is running and credentials are correct in `application.properties`:
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/gyana_social
  spring.datasource.username=root
  spring.datasource.password=Gyana@1234qw
  ```

### Issue: JWT Token Invalid
- **Solution:** 
  - Re-login to get a new token
  - Ensure token starts with "Bearer " prefix in Authorization header
  - Check if token has expired (valid for 10 days)

### Issue: Email Already Exists
- **Solution:** Use a unique email address for each signup test

### Issue: User/Post/Comment Not Found
- **Solution:** Verify the ID exists in the database by fetching all records first

---

## Tips for Effective Testing

1. **Use Environment Variables:** Set `base_url` to switch between environments
2. **Save Requests:** Organize requests in folders within your collection
3. **Use Pre-request Scripts:** Automate token extraction
4. **Collection Runner:** Test all endpoints in sequence
5. **Generate Documentation:** Use Postman's documentation feature to share API docs
6. **Export Results:** Generate test reports for team review

---

## Database Schema Overview

**Users Table:** Stores user credentials and profile info
**Posts Table:** Stores user posts with timestamps
**Comments Table:** Stores comments linked to posts and users
**Post_Likes:** Tracks which users liked which posts
**Saved_Posts:** Tracks which posts users have saved

---

## Additional Resources

- [Postman Documentation](https://learning.postman.com/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [JWT Authentication Guide](https://jwt.io/)
- [RESTful API Best Practices](https://restfulapi.net/)

---

## Support

For issues or questions, check the application logs:
```bash
# If running with Maven
mvn spring-boot:run (logs will display in terminal)

# Check application.properties for configuration
```

---

**Last Updated:** March 17, 2026
**Project:** Gyana Social API
**Version:** 1.0.0
