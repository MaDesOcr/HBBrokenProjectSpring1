package com.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mddapi.entities.Post;
import com.mddapi.entities.Subject;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findBySubject(Subject subject);

    Optional<Post> findById(Long id);

}
