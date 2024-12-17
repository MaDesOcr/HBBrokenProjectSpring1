package com.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mddapi.entities.Comment;
import com.mddapi.entities.Post;
import com.mddapi.entities.User;
import com.mddapi.payload.request.CommentRequest;
import com.mddapi.repositories.CommentRepository;
import com.mddapi.repositories.PostRepository;
import com.mddapi.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public void addComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(commentRequest.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }


    public List<Comment> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return commentRepository.findByPost(post);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
