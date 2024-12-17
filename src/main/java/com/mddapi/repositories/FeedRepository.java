package com.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mddapi.entities.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
}
