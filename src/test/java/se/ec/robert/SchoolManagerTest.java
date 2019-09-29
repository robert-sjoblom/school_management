package se.ec.robert;

import static org.junit.Assert.*;

import java.time.LocalDate;
import se.ec.robert.models.Course;
import se.ec.robert.models.Student;
import org.junit.Before;
import org.junit.Test;
import se.ec.robert.data_access.CourseDao;
import se.ec.robert.data_access.CourseDaoList;
import se.ec.robert.data_access.StudentDao;
import se.ec.robert.data_access.StudentDaoList;

public class SchoolManagerTest {
  SchoolManager manager;
  Course course;
  Student student;

  @Before
  public void setUp() {
    StudentDao studentDaoList = new StudentDaoList();
    CourseDao courseDaoList = new CourseDaoList();
    course = new Course(1, "Math", LocalDate.parse("2019-01-01"), 12);
    student = new Student(1, "Bob", "bob@example.com", "Backen 2");
    manager = new SchoolManager(courseDaoList, studentDaoList);
  }

  @Test
  public void createCourse() {
    assertEquals(course, manager.createCourse("Math, 2019-01-01, 12"));
  }

  @Test
  public void createStudent() {
    assertEquals(student, manager.createStudent("Bob, bob@example.com, Backen 2"));
  }

  @Test
  public void registerStudentOnCourse() {
    manager.createCourse("Math, 2019-01-01, 12");
    manager.createStudent("Bob, bob@example.com, Backen 2");
    manager.registerStudentOnCourse(1, 1);

    assertEquals(student,
                 manager.findCourseById(1).getStudents().get(0));
  }

  @Test
  public void removeStudentFromCourse() {
    manager.createCourse("Math, 2019-01-01, 12");
    manager.createStudent("Bob, bob@example.com, Backen 2");
    manager.removeStudentFromCourse(1, 1);

    assertEquals(0,
                 manager.findCourseById(1).getStudents().size());
  }

  @Test
  public void editStudent() {
    Student student = new Student(1, "John", "bob@example.com", "Aaaa");
    manager.createStudent("Bob, bob@example.com, Backen 2");
    manager.editStudent(1, "John", "bob@example.com", "Aaaa");
    assertEquals(student, manager.findStudentById(1));
  }

  @Test
  public void editCourse() {
    Course course = new Course(1, "A",
                               LocalDate.of(2019,
                                            01,
                                            01),
                               12);
    manager.createCourse("Math, 2019-01-01, 12");
    manager.editCourse(1, "A", LocalDate.parse("2019-01-01"), 12);
    assertEquals(course, manager.findCourseById(1));
  }
  @Test
  public void removeNonExistingStudentFromCourse() {
    manager.createCourse("Math, 2019-01-01, 12");
    manager.createStudent("Bob, bob@example.com, Backen 2");
    manager.removeStudentFromCourse(2, 1);

    assertEquals(0,
                 manager.findCourseById(1).getStudents().size());
  }
}
