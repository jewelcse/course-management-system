package com.bjit.student.repository;

import com.bjit.student.dto.response.CourseResponseDto;
import com.bjit.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "select c.course_name, c.course_description, c.author_name, c.genre, " +
            "c.teacher_id, c.course_review from courses c join course_enrollments e on c.id = e.course_id " +
            "where e.student_id =:id",nativeQuery = true)
    List<CourseResponseDto> getEnrolledCourses(@Param("id") long id);
}
