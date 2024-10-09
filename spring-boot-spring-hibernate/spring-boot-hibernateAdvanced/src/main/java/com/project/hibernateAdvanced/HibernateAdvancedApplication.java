package com.project.hibernateAdvanced;

import com.project.hibernateAdvanced.dao.AppDao;
import com.project.hibernateAdvanced.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class HibernateAdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateAdvancedApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner-> {
			deleteStudent(appDao);
		};

	}

	private void deleteStudent(AppDao appDao) {
		int theId=1;
		appDao.deleteStudent(theId);
	}

	private void addMoreCoursesForStudent(AppDao appDao) {
		int theId=1;
		Student student = appDao.findCourseAndStudentByStudentId(theId);

		Course theCourse = new Course("History lessons");
		Course theCourse1= new Course("Music lessons");

		student.addCourse(theCourse);
		student.addCourse(theCourse1);

		appDao.update(student);
	}

	private void findStudentAndCourse(AppDao appDao) {
		Student student = appDao.findCourseAndStudentByStudentId(1);
		System.out.println("Sudent : " + student);
		System.out.println("Couses: " + student.getCourses());
	}

	private void findCourseAndStudent(AppDao appDao) {
		Course theCourse= appDao.findCourseAndStudentByCourseId(10);
		System.out.println("Course: " +theCourse);
		System.out.println("Student: " +theCourse.getStudents());
	}

	private void createCourseAndStudent(AppDao appDao) {

		Course theCourse = new Course("Math lessons");

		Student student = new Student("Mehmet","Yilmaz","mehmetyilmaz@gmail.com");
		Student student1 = new Student("Ali","Erkin","alierkin@gmail.com");

		theCourse.addStudent(student);
		theCourse.addStudent(student1);

		appDao.save(theCourse);

	}

	private void deleteCourseAndReviews(AppDao appDao) {
		appDao.deleteCourseById(10);

	}

	private void retrieveCourseAndReviews(AppDao appDao) {
		Course theCourse=appDao.findCourseAndReviewsByCourseId(10);
		System.out.println(theCourse);
		System.out.println(theCourse.getReviews());
	}

	private void createCourseAndReview(AppDao appDao) {
		Course course = new Course("Spanish lessons");
		Review theReview=new Review("This course is for beginners");
		course.addReview(theReview);
		course.addReview(new Review("Only 20 dolar!!"));
		appDao.save(course);

	}

	private void deleteCourse(AppDao appDao) {
		int id=10;
		appDao.deleteCourseById(id);

	}

	private void updateCourse(AppDao appDao) {
		Course theCourse = appDao.findCourseById(10);
		theCourse.setTitle("English lessons");
		appDao.update(theCourse);
	}

	private void updateInstructor(AppDao appDao) {
		Instructor theInstructor = appDao.findInstructorById(1);
		theInstructor.setLastName("Yildirim");
		appDao.update(theInstructor);

	}

	private void findInstructorWithCoursesByJoinFetch(AppDao appDao) {
		Instructor theInstructor = appDao.findInstructorByJoinFetch(1);
		System.out.println("Instructor Info: " + theInstructor);
		System.out.println("Courses : " + theInstructor.getCourses());

	}

	private void findCoursesForInstructor(AppDao appDao) {
		int theId=1;
		Instructor theInstructor=appDao.findInstructorById(theId);
		System.out.println("Instructor info:" + theInstructor);
		List<Course>courses=appDao.findCoursesByInstructorId(theId);
		theInstructor.setCourses(courses);
		System.out.println("Courses: " +  theInstructor.getCourses());
	}

	private void findInstructorWithCouurses(AppDao appDao) {
		Instructor theInstructor = appDao.findInstructorById(1);
		System.out.println("Instructor info:" + theInstructor);
		System.out.println("Courses:" + theInstructor.getCourses());
	}

	public void createInstructor(AppDao appDao){
		Instructor theInstructor =new Instructor("irem","kartal","iremkartal1728@gmail.com");
		InstructorDetails theInstructorDetails= new InstructorDetails("iremkartal","coding");
		theInstructor.setInstructorDetails(theInstructorDetails);
		appDao.save(theInstructor);

	}
	public void deleteInstructor(AppDao appDao){
		Instructor instructor=appDao.findInstructorById(5);
		appDao.deleteInstructorById(instructor.getId());
	}
	public void deleteInstructorDetails(AppDao appDao){
		InstructorDetails instructorDetails = appDao.findInstructorDetailsById(2);
		appDao.deleteInstructorDetailsById(instructorDetails.getId());
	}
	public void createInstructorWithCourses(AppDao appDao){
		Instructor theInstructor=new Instructor("Yusuf","Kartal","yusufkartal05@gmail.com");
		InstructorDetails theInstructorDetails= new InstructorDetails("yusufkartal","driving");
		theInstructor.setInstructorDetails(theInstructorDetails);
		Course course=new Course("Driving lessons");
		Course course1=new Course("English lessons");
		theInstructor.add(course);
		theInstructor.add(course1);
		appDao.save(theInstructor);
	}

}
