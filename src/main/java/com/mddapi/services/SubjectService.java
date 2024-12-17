package com.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mddapi.entities.Post;
import com.mddapi.entities.Subject;
import com.mddapi.repositories.SubjectRepository;

import java.util.List;


public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Post> getPostsBySubjectId(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject not found"));
        return subject.getPosts();
    }
}
