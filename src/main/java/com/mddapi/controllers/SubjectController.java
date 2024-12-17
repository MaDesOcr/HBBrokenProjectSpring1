package com.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mddapi.entities.Post;
import com.mddapi.entities.Subject;
import com.mddapi.payload.request.SubjectRequest;
import com.mddapi.payload.response.MessageResponse;
import com.mddapi.repositories.PostRepository;
import com.mddapi.repositories.SubjectRepository;
import com.mddapi.services.SubjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<?> createSubject(@Valid @RequestBody SubjectRequest subjectRequest) {
        Subject subject = new Subject()
                .setName("newName")
                .setDescription("newDescription");
        subjectRepository.save(subject);

        return ResponseEntity.ok(new MessageResponse("Subject created successfully!"));
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
        return ResponseEntity.ok(subject);
    }
    @GetMapping("/{subjectId}/posts")
    public ResponseEntity<List<Post>> getPostsBySubject(@PathVariable Long subjectId) {
        List<Post> posts = subjectService.getPostsBySubjectId(subjectId);
        return ResponseEntity.ok(posts);
    }
}
