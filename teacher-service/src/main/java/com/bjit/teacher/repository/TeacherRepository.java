package com.bjit.teacher.repository;

import com.bjit.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher findByEmail(String email);
    boolean existsByEmail(String email);
}
