package se.ec.robert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import models.Course;
import models.Student;
import se.ec.robert.data_access.CourseDao;
import se.ec.robert.data_access.CourseDaoList;
import se.ec.robert.data_access.StudentDao;
import se.ec.robert.data_access.StudentDaoList;

public class SchoolManager {
  private final CourseDao courseDao;
  private final StudentDao studentDao;
  private int studentId;
  private int courseId;

  public SchoolManager(CourseDao courseDao, StudentDao studentDao) {
    this.courseDao = courseDao;
    this.studentDao = studentDao;
  }

  public SchoolManager() {
    this.courseDao = new CourseDaoList();
    this.studentDao = new StudentDaoList();
  }

  public void registerStudentOnCourse(int studentId, int courseId) {
    try {
      Course course = findCourseById(courseId);
      course.register(studentDao.findById(studentId));
    } catch (NoSuchElementException e) {}
  }

  public void removeStudentFromCourse(int studentId, int courseId) {
    try {
      Course course = findCourseById(courseId);
      Student student = findStudentById(studentId);
      course.unregister(student);
    } catch (NoSuchElementException e) {}
  }

  public Student editStudent(int id, String name, String email, String address) {
    Student student = getStudentWithId(id, name, email, address);

    return studentDao.saveStudent(student);
  }

  public Course editCourse(int id, String name, LocalDate date, int weeks) {
    Course course = getCourseWithId(id, name, date, weeks);

    return courseDao.saveCourse(course);
  }
  /*
    StudentDao delegation
   */
  public Student createStudent(String input) {
    final Student student = getStudentFromString(input);
    return studentDao.saveStudent(student);
  }

  public Student findStudentById(int studentId) {
    return studentDao.findById(studentId);
  }

  public ArrayList<Student> findStudentByName(String name) {
    return studentDao.findByName(name);
  }

  public Student findStudentByEmail(String email) {
    return studentDao.findByEmail(email);
  }

  public ArrayList<Student> findAllStudents() {
    return studentDao.findAll();
  }

  /*
  CourseDao delegation
   */
  public Course findCourseById(int courseId) {
    return courseDao.findById(courseId);
  }

  public ArrayList<Course> findCourseByName(String name) {
    return courseDao.findByName(name);
  }

  public ArrayList<Course> findCourseByDate(LocalDate date) {
    // we expect perfect input at this layer
    return courseDao.findByDate(date);
  }

  public ArrayList<Course> findAllCourses() {
    return courseDao.findAll();
  }

  public Course createCourse(String input) {
    final Course course = getCourseFromString(input);
    return courseDao.saveCourse(course);
  }

  private Course getCourseFromString(String input) {
    String[] splitInput = input.split(",");

    String name = splitInput[0].trim();
    LocalDate date = LocalDate.parse(splitInput[1].trim());
    int weeks = new Integer(splitInput[2].trim());
    return new Course(++courseId, name, date, weeks);
  }
  private Course getCourseWithId(int id, String name, LocalDate date, int weeks) {
    return new Course(id, name, date, weeks);
  }
  private Student getStudentFromString(String input) {
    String[] splitInput = input.split(",");
    String name = splitInput[0].trim();
    String email = splitInput[1].trim();
    String address = splitInput[2].trim();

    return new Student(++studentId, name, email, address);
  }
  private Student getStudentWithId(int id, String name, String email, String address) {
    return new Student(id, name, email, address);
  }
}
