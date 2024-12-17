package com.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mddapi.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
