package com.project.hibernateAdvanced.dao;

import com.project.hibernateAdvanced.entity.Course;
import com.project.hibernateAdvanced.entity.Instructor;
import com.project.hibernateAdvanced.entity.InstructorDetails;
import com.project.hibernateAdvanced.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

    private EntityManager entityManager;

    @Autowired
    public AppDaoImpl (EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
       Instructor theInstructor= entityManager.find(Instructor.class,theId);
        return theInstructor;
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor theInstructor=entityManager.find(Instructor.class,theId);
        List<Course> courses = theInstructor.getCourses();

        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetails findInstructorDetailsById(int theId) {
        InstructorDetails instructorDetails= entityManager.find(InstructorDetails.class,theId);
        return instructorDetails;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int theId) {
        InstructorDetails instructorDetails=entityManager.find(InstructorDetails.class,theId);
        instructorDetails.getInstructor().setInstructorDetails(null);
        entityManager.remove(instructorDetails);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query= entityManager.createQuery("from Course where instructor.id= :data",Course.class);
        query.setParameter("data",theId);
        List<Course> courses=query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByJoinFetch(int id) {
        TypedQuery<Instructor> query=entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + " JOIN FETCH i.instructorDetails "
                        + " where i.id = :data ",Instructor.class);

        query.setParameter("data",id);
        Instructor theInstructor=query.getSingleResult();
        return theInstructor;

    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int id) {
        Course course = entityManager.find(Course.class,id);
        return course;
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course theCourse = entityManager.find(Course.class,10);
        entityManager.remove(theCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                    "select c from Course c "
                            + "JOIN FETCH c.reviews "
                            +  " where c.id = :data " , Course.class
        );
        query.setParameter("data",theId);
        Course theCourse = query.getSingleResult();
        return theCourse;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        " JOIN FETCH c.students"
                        +" where c.id= :data " ,Course.class

                );
        query.setParameter("data",theId);
        Course theCourse = query.getSingleResult();
        return theCourse;
    }

    @Override
    public Student findCourseAndStudentByStudentId(int theId) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + " JOIN FETCH s.courses "
                + "where s.id= :data" ,Student.class);
        query.setParameter("data",theId);
        Student student=query.getSingleResult();
        return student;
    }
    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudent(int theId) {
      Student student=  entityManager.find(Student.class,theId);
      entityManager.remove(student);
    }
}
