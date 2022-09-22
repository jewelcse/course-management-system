package com.bjit.course.repository;

import com.bjit.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{
    List<Course> findAllByTeacherId(Long teacherId);
}
