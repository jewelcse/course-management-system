package com.bjit.course.repository;

import com.bjit.course.entity.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment,Long> {
    boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);

    List<CourseEnrollment> findByStudentId(Long id);

    List<CourseEnrollment> findByCourseId(Long id);
}
