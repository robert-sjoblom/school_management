package se.ec.robert.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
  private Course course;
  @Before
  public void setUp() {
    course = new Course(1,
                        "Mathematics",
                        LocalDate.parse("2014-01-01"),
                        2);
  }

  @After
  public void tearDown() {
    course.clear();
  }

  @Test
  public void register() {
    course.register(new Student(1, "Bob", "bob@example.com", "Backen 2"));
    List<Student> expected = new ArrayList<>();

    assertEquals(course.getStudents().get(0),
                 new Student(1, "Bob", "bob@example.com", "Backen 2"));
  }

  @Test
  public void unregister() {
    Student student = new Student(1, "Bob", "bob@example.com", "Backen 2");
    course.register(student);
    course.unregister(student);

    assertTrue(course.getStudents().isEmpty());
  }

  @Test
  public void testToString() {
    assertEquals(
        "Course{id=1, courseName='Mathematics', startDate=2014-01-01, weekDuration=2, students=[]}",
        course.toString()
    );
  }
}