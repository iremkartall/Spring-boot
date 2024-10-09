package com.project.hibernateAdvanced.dao;

import com.project.hibernateAdvanced.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);

    InstructorDetails findInstructorDetailsById(int theId);
    void deleteInstructorDetailsById(int theId);

    List<Course>findCoursesByInstructorId(int id);
    Instructor findInstructorByJoinFetch(int id);
    void update(Instructor theInstructor);
    void update(Course theCourse);

    Course findCourseById(int id);
    void deleteCourseById(int id);
    void save(Course theCourse);
    Course findCourseAndReviewsByCourseId(int theId);
    Course findCourseAndStudentByCourseId(int theId);

    Student findCourseAndStudentByStudentId(int theId);
    void update(Student student);

    void deleteStudent(int theId);


}
