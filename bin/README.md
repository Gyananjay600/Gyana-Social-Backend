# Gyana Social - API Documentation

## Overview
Gyana Social is a Spring Boot-based social media application with features for creating posts, comments, and reels. This document provides complete API endpoint documentation and Postman testing examples.

---

## Table of Contents
1. [Authentication](#authentication)
2. [Users Endpoints](#users-endpoints)
3. [Posts Endpoints](#posts-endpoints)
4. [Comments Endpoints](#comments-endpoints)
5. [Reels Endpoints](#reels-endpoints)
6. [Testing with Postman](#testing-with-postman)

---

## Authentication

All protected endpoints require a JWT token in the `Authorization` header.

**Header Format:**
```
Authorization: Bearer <jwt_token>
```

### Get JWT Token (Login)
- **Endpoint**: `POST /api/auth/login`
- **Body**:
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

---

## Users Endpoints

### 1. Get All Users
- **Endpoint**: `GET /api/users`
- **Authentication**: Not required
- **Response**: List of all users
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "gender": "Male",
    "followers": [2, 3],
    "followings": [4, 5]
  }
]
```

### 2. Get User by ID
- **Endpoint**: `GET /api/users/{userid}`
- **Authentication**: Not required
- **Parameter**: `userid` (Integer)
- **Example**: `GET /api/users/1`

### 3. Get Current User Profile
- **Endpoint**: `GET /api/users/profile`
- **Authentication**: Required (JWT)
- **Response**: Current logged-in user details

### 4. Update User
- **Endpoint**: `PUT /api/users`
- **Authentication**: Required (JWT)
- **Body**:
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "gender": "Female"
}
```

### 5. Follow User
- **Endpoint**: `PUT /api/users/follow/{userid2}`
- **Authentication**: Required (JWT)
- **Parameter**: `userid2` (Integer) - ID of user to follow
- **Example**: `PUT /api/users/follow/2`
- **Response**: Updated user object

### 6. Search User
- **Endpoint**: `GET /api/users/search?query={searchTerm}`
- **Authentication**: Not required
- **Query Parameter**: `query` (String)
- **Example**: `GET /api/users/search?query=john`

### 7. Delete User
- **Endpoint**: `DELETE /api/users/{userid}`
- **Authentication**: Not required
- **Parameter**: `userid` (Integer)
- **Note**: Deletes all associated posts, comments, and reels

---

## Posts Endpoints

### 1. Create Post
- **Endpoint**: `POST /api/posts`
- **Authentication**: Required (JWT)
- **Content-Type**: `application/json`
- **Body**:
```json
{
  "caption": "Beautiful sunset!",
  "image": "https://example.com/image.jpg",
  "video": "https://example.com/video.mp4"
}
```
- **Response**: Created post object with ID, createdAt timestamp, etc.

### 2. Get All Posts
- **Endpoint**: `GET /api/posts`
- **Authentication**: Not required
- **Response**: List of all posts with user details (password excluded)

### 3. Get Post by ID
- **Endpoint**: `GET /api/posts/{postId}`
- **Authentication**: Not required
- **Parameter**: `postId` (Integer)
- **Example**: `GET /api/posts/5`

### 4. Get User's Posts
- **Endpoint**: `GET /api/posts/user/{userId}`
- **Authentication**: Not required
- **Parameter**: `userId` (Integer)
- **Example**: `GET /api/posts/user/1`
- **Response**: List of posts created by the user

### 5. Like Post
- **Endpoint**: `PUT /api/posts/like/{postId}`
- **Authentication**: Required (JWT)
- **Parameter**: `postId` (Integer)
- **Example**: `PUT /api/posts/like/5`
- **Note**: Toggles like status - if liked, removes like; if not liked, adds like

### 6. Save Post
- **Endpoint**: `PUT /api/posts/save/{postId}`
- **Authentication**: Required (JWT)
- **Parameter**: `postId` (Integer)
- **Example**: `PUT /api/posts/save/5`
- **Note**: Toggles save status

### 7. Delete Post
- **Endpoint**: `DELETE /api/posts/{postId}`
- **Authentication**: Required (JWT)
- **Parameter**: `postId` (Integer)
- **Note**: Only post creator can delete their own posts

---

## Comments Endpoints

### 1. Create Comment
- **Endpoint**: `POST /api/comments/post/{postId}/user/{userId}`
- **Authentication**: Not required
- **Parameters**: 
  - `postId` (Integer) - ID of the post to comment on
  - `userId` (Integer) - ID of the user creating the comment
- **Body**:
```json
{
  "content": "Great post!"
}
```
- **Response**: Created comment object

### 2. Get All Comments
- **Endpoint**: `GET /api/comments`
- **Authentication**: Not required
- **Response**: List of all comments

### 3. Get Comment by ID
- **Endpoint**: `GET /api/comments/{commentId}`
- **Authentication**: Not required
- **Parameter**: `commentId` (Integer)

### 4. Get Comments by Post ID
- **Endpoint**: `GET /api/comments/post/{postId}`
- **Authentication**: Not required
- **Parameter**: `postId` (Integer)
- **Response**: List of all comments on a specific post

### 5. Get Comments by User ID
- **Endpoint**: `GET /api/comments/user/{userId}`
- **Authentication**: Not required
- **Parameter**: `userId` (Integer)
- **Response**: List of all comments created by a user

### 6. Update Comment
- **Endpoint**: `PUT /api/comments/{commentId}/user/{userId}`
- **Authentication**: Not required
- **Parameters**:
  - `commentId` (Integer)
  - `userId` (Integer)
- **Body**:
```json
{
  "content": "Updated comment text"
}
```

### 7. Delete Comment
- **Endpoint**: `DELETE /api/comments/{commentId}/user/{userId}`
- **Authentication**: Not required
- **Parameters**:
  - `commentId` (Integer)
  - `userId` (Integer)

---

## Reels Endpoints

### 1. Create Reel
- **Endpoint**: `POST /api/reels`
- **Authentication**: Required (JWT)
- **Content-Type**: `application/json`
- **Body**:
```json
{
  "title": "Amazing Dance Video",
  "description": "Dancing to the latest trending song",
  "videoUrl": "https://example.com/video.mp4",
  "thumbnail": "https://example.com/thumbnail.jpg"
}
```
- **Response**: Created reel object with ID, timestamps, view count (0), like count (0)
- **Example Response**:
```json
{
  "id": 1,
  "title": "Amazing Dance Video",
  "description": "Dancing to the latest trending song",
  "videoUrl": "https://example.com/video.mp4",
  "thumbnail": "https://example.com/thumbnail.jpg",
  "likeCount": 0,
  "viewCount": 0,
  "createdAt": "2024-03-19T10:30:00"
}
```

### 2. Get All Reels
- **Endpoint**: `GET /api/reels`
- **Authentication**: Not required
- **Response**: List of all reels with user details, like counts, view counts
- **Example Response**:
```json
[
  {
    "id": 1,
    "title": "Amazing Dance Video",
    "description": "Dancing to the latest trending song",
    "videoUrl": "https://example.com/video.mp4",
    "thumbnail": "https://example.com/thumbnail.jpg",
    "likeCount": 5,
    "viewCount": 150,
    "createdAt": "2024-03-19T10:30:00"
  }
]
```

### 3. Get Reel by ID
- **Endpoint**: `GET /api/reels/{reelId}`
- **Authentication**: Not required
- **Parameter**: `reelId` (Integer)
- **Example**: `GET /api/reels/1`
- **Response**: Single reel object with all details

### 4. Get User's Reels
- **Endpoint**: `GET /api/reels/user/{userId}`
- **Authentication**: Not required
- **Parameter**: `userId` (Integer)
- **Example**: `GET /api/reels/user/1`
- **Response**: List of reels created by the specified user
```json
[
  {
    "id": 1,
    "title": "My First Reel",
    "description": "Check out my first reel",
    "videoUrl": "https://example.com/video1.mp4",
    "thumbnail": "https://example.com/thumb1.jpg",
    "likeCount": 10,
    "viewCount": 200,
    "createdAt": "2024-03-19T10:30:00"
  },
  {
    "id": 2,
    "title": "My Second Reel",
    "description": "Another awesome reel",
    "videoUrl": "https://example.com/video2.mp4",
    "thumbnail": "https://example.com/thumb2.jpg",
    "likeCount": 8,
    "viewCount": 150,
    "createdAt": "2024-03-18T15:45:00"
  }
]
```

### 5. Like Reel
- **Endpoint**: `PUT /api/reels/like/{reelId}`
- **Authentication**: Required (JWT)
- **Parameter**: `reelId` (Integer)
- **Example**: `PUT /api/reels/like/1`
- **Note**: Toggles like status and updates likeCount
- **Response**: Updated reel object with new likeCount

### 6. Increment View Count
- **Endpoint**: `PUT /api/reels/view/{reelId}`
- **Authentication**: Not required
- **Parameter**: `reelId` (Integer)
- **Example**: `PUT /api/reels/view/1`
- **Note**: Increments viewCount by 1, call this when a user watches the reel
- **Response**: Updated reel object with incremented viewCount

### 7. Delete Reel
- **Endpoint**: `DELETE /api/reels/{reelId}`
- **Authentication**: Required (JWT)
- **Parameter**: `reelId` (Integer)
- **Example**: `DELETE /api/reels/1`
- **Note**: Only reel creator can delete their own reels
- **Response**: 
```json
{
  "message": "reel deleted successfully",
  "success": true
}
```

---

## Testing with Postman

### Setup Postman Environment

1. **Create a new Postman Collection** named "Gyana Social API"
2. **Create Environment Variables**:
   - `baseUrl`: `http://localhost:8080` (adjust port as needed)
   - `jwtToken`: Will be set after login

### Step-by-Step Testing Guide

#### Step 1: User Registration/Login
1. Send a POST request to login and get the JWT token
2. Copy the JWT token from the response
3. Set it in Postman environment: `pm.environment.set("jwtToken", response.token);`

#### Step 2: Test Reels Endpoints

##### Create a Reel (Requires JWT)
```
POST {{baseUrl}}/api/reels
Headers: 
  - Authorization: Bearer {{jwtToken}}
  - Content-Type: application/json

Body:
{
  "title": "Sunset Vibes",
  "description": "Beautiful sunset at the beach",
  "videoUrl": "https://example.com/sunset.mp4",
  "thumbnail": "https://example.com/sunset-thumb.jpg"
}
```

##### Get All Reels
```
GET {{baseUrl}}/api/reels
```

##### Get User's Reels
```
GET {{baseUrl}}/api/reels/user/1
```

##### Get Reel by ID
```
GET {{baseUrl}}/api/reels/1
```

##### Increment View Count
```
PUT {{baseUrl}}/api/reels/view/1
```

##### Like a Reel (Requires JWT)
```
PUT {{baseUrl}}/api/reels/like/1
Headers:
  - Authorization: Bearer {{jwtToken}}
```

##### Delete a Reel (Requires JWT)
```
DELETE {{baseUrl}}/api/reels/1
Headers:
  - Authorization: Bearer {{jwtToken}}
```

#### Step 3: Test Posts Endpoints

##### Create Post (Requires JWT)
```
POST {{baseUrl}}/api/posts
Headers:
  - Authorization: Bearer {{jwtToken}}
  - Content-Type: application/json

Body:
{
  "caption": "Amazing view from today!",
  "image": "https://example.com/image.jpg",
  "video": "https://example.com/video.mp4"
}
```

##### Get All Posts
```
GET {{baseUrl}}/api/posts
```

##### Like Post (Requires JWT)
```
PUT {{baseUrl}}/api/posts/like/1
Headers:
  - Authorization: Bearer {{jwtToken}}
```

##### Delete Post (Requires JWT)
```
DELETE {{baseUrl}}/api/posts/1
Headers:
  - Authorization: Bearer {{jwtToken}}
```

#### Step 4: Test Comments Endpoints

##### Create Comment
```
POST {{baseUrl}}/api/comments/post/1/user/2
Content-Type: application/json

Body:
{
  "content": "Great post! Love this content."
}
```

##### Get Comments on Post
```
GET {{baseUrl}}/api/comments/post/1
```

##### Delete Comment
```
DELETE {{baseUrl}}/api/comments/1/user/2
```

### Postman Collection JSON Template

Create a new collection with these requests:

```json
{
  "info": {
    "name": "Gyana Social API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Reels",
      "item": [
        {
          "name": "Create Reel",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer {{jwtToken}}"
              },
              {
                "key": "Content-Type",
                "value": "application/json"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\n  \"title\": \"Sunset Vibes\",\n  \"description\": \"Beautiful sunset at the beach\",\n  \"videoUrl\": \"https://example.com/sunset.mp4\",\n  \"thumbnail\": \"https://example.com/sunset-thumb.jpg\"\n}"
            },
            "url": {
              "raw": "{{baseUrl}}/api/reels",
              "host": ["{{baseUrl}}"],
              "path": ["api", "reels"]
            }
          }
        },
        {
          "name": "Get All Reels",
          "request": {
            "method": "GET",
            "url": {
              "raw": "{{baseUrl}}/api/reels",
              "host": ["{{baseUrl}}"],
              "path": ["api", "reels"]
            }
          }
        },
        {
          "name": "Get User's Reels",
          "request": {
            "method": "GET",
            "url": {
              "raw": "{{baseUrl}}/api/reels/user/1",
              "host": ["{{baseUrl}}"],
              "path": ["api", "reels", "user", "1"]
            }
          }
        },
        {
          "name": "Like Reel",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer {{jwtToken}}"
              }
            ],
            "url": {
              "raw": "{{baseUrl}}/api/reels/like/1",
              "host": ["{{baseUrl}}"],
              "path": ["api", "reels", "like", "1"]
            }
          }
        },
        {
          "name": "Increment View",
          "request": {
            "method": "PUT",
            "url": {
              "raw": "{{baseUrl}}/api/reels/view/1",
              "host": ["{{baseUrl}}"],
              "path": ["api", "reels", "view", "1"]
            }
          }
        },
        {
          "name": "Delete Reel",
          "request": {
            "method": "DELETE",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer {{jwtToken}}"
              }
            ],
            "url": {
              "raw": "{{baseUrl}}/api/reels/1",
              "host": ["{{baseUrl}}"],
              "path": ["api", "reels", "1"]
            }
          }
        }
      ]
    }
  ]
}
```

### Expected Response Codes

| Status Code | Meaning |
|------------|---------|
| 200 | OK - Request successful |
| 201 | Created - New resource created successfully |
| 400 | Bad Request - Invalid request format |
| 401 | Unauthorized - JWT token missing or invalid |
| 403 | Forbidden - User not authorized to perform action |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error |

### Error Response Format

```json
{
  "message": "Error description",
  "success": false
}
```

### Success Response Format for API Responses

```json
{
  "message": "Operation successful",
  "success": true
}
```

---

## Database Schema

### Reels Table
```sql
CREATE TABLE reels (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  video_url VARCHAR(500),
  thumbnail VARCHAR(500),
  user_id INT,
  like_count INT DEFAULT 0,
  view_count INT DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Reel Likes Table (Many-to-Many)
```sql
CREATE TABLE reels_liked (
  reel_id INT,
  liked_id INT,
  PRIMARY KEY (reel_id, liked_id),
  FOREIGN KEY (reel_id) REFERENCES reels(id),
  FOREIGN KEY (liked_id) REFERENCES users(id)
);
```

---

## Common Issues & Solutions

### Issue: 401 Unauthorized when calling protected endpoints
**Solution**: 
- Ensure JWT token is correctly set in `Authorization` header
- Check if token has expired
- Verify the format is `Bearer <token>`

### Issue: 404 Not Found for Reel endpoints
**Solution**:
- Verify the reelId exists in the database
- Check if the reelId is a valid integer

### Issue: 403 Forbidden when deleting reel
**Solution**:
- Only the reel creator can delete their own reels
- Use the JWT token of the reel creator in the request

### Issue: CORS errors
**Solution**:
- Add CORS configuration in your Spring Boot application if frontend is on different domain
- Configure in `AppConfig.java` or as a separate CORS configuration class

---

## Reel Features Summary

✅ **Create Reels** - Users can create reels with title, description, video URL, and thumbnail
✅ **View All Reels** - Get all reels from all users (sorted by creation date)
✅ **Get User Reels** - Retrieve all reels created by a specific user
✅ **Like/Unlike Reels** - Toggle like status with automatic like count updates
✅ **View Counter** - Track number of views for each reel
✅ **Delete Reels** - Only creator can delete their own reels
✅ **User Deletion** - When a user is deleted, all their reels are deleted automatically

---

## Next Steps / Future Enhancements

- Add reel comments functionality
- Add reel search by title or description
- Add trending reels (sorted by views/likes)
- Add reel categories/hashtags
- Add reel sharing features
- Add reel analytics for creators
- Implement reel recommendations algorithm

---

## Support & Contact

For issues or questions, please contact the development team or create an issue in the repository.

---

**Version**: 1.0.0  
**Last Updated**: March 19, 2024  
**API Base URL**: `http://localhost:8080`
