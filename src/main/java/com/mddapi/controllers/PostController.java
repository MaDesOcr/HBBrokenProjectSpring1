package com.mddapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mddapi.entities.Post;
import com.mddapi.entities.Subject;
import com.mddapi.entities.User;
import com.mddapi.payload.request.PostRequest;
import com.mddapi.payload.response.MessageResponse;
import com.mddapi.repositories.PostRepository;
import com.mddapi.repositories.SubjectRepository;
import com.mddapi.repositories.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostRepository postRepository;

    private UserRepository userRepository;

    private SubjectRepository subjectRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest) {
        // Retrieve user and subject from repositories
        User user = userRepository.findById(postRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + postRequest.getUserId()));
        Subject subject = subjectRepository.findById(postRequest.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with id " + postRequest.getSubjectId()));

        // Create and save the post
        Post post = new Post();
                

        postRepository.save(post);

        return ResponseEntity.ok(new MessageResponse("Post created successfully!"));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        logger.info("Fetching all posts");
        List<Post> posts = postRepository.findAll();
        logger.info("Number of posts found: {}", posts.size());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        logger.info("Fetching post with id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(new MessageResponse("Post updated successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        logger.info("Deleting post with id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));

        postRepository.delete(post);
        return ResponseEntity.ok(new MessageResponse("Post deleted successfully!"));
    }
}
