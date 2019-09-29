package se.ec.robert.data_access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import se.ec.robert.models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.ec.robert.models.Course;

/**
 * Unit test for simple App.
 */
public class CourseDaoListTest {
  private CourseDaoList courseDaoList;
  private Course course = new Course(1, "Math",
                                     LocalDate.parse("2019-01-01"),
                                     12);
  @Before
  public void setUp() {
    course.register(new Student(
        1, "Bob", "b@b.b", "Backen 2")
    );
    courseDaoList = new CourseDaoList();
  }

  @After
  public void tearDown() {
    courseDaoList.clear();
    course.clear();
  }

  @Test
  public void saveCourse() {
    assertEquals(0, courseDaoList.size());

    courseDaoList.saveCourse(course);

    assertEquals(1, courseDaoList.size());
  }

  @Test
  public void editCourse() {
    courseDaoList.saveCourse(course);

    Course course2 = new Course(1, "Sci",
                        LocalDate.parse("2019-01-01"),
                        12);

    courseDaoList.saveCourse(course2);

    assertEquals(1, courseDaoList.size());
    assertEquals(course2, courseDaoList.findById(1));
    // a coarse test, but at least we ensure that students follow on edits:
    assertEquals(1, courseDaoList.findById(1).getStudents().size());
    assertEquals(new Student(1, "Bob", "b@b.b", "Backen 2"),
                 courseDaoList.findById(1).getStudents().get(0));
  }

  @Test
  public void findById() {
    courseDaoList.saveCourse(course);

    assertEquals(course, courseDaoList.findById(1));
  }

  @Test
  public void findByName() {
    Course course2 = new Course(2, "Math",
                                LocalDate.parse("2019-02-01"), 12);
    courseDaoList.saveCourse(course);
    courseDaoList.saveCourse(course2);
    assertEquals(2, courseDaoList.findByName("math").size());
  }

  @Test
  public void findByDate() {
    Course course2 = new Course(2, "Math",
                                LocalDate.parse("2019-02-01"), 12);
    courseDaoList.saveCourse(course);
    courseDaoList.saveCourse(course2);
    assertEquals(course2, courseDaoList.findByDate(LocalDate.parse("2019-02-01"))
        .get(0));
  }

  @Test
  public void removeCourse() {
    courseDaoList.saveCourse(course);
    courseDaoList.removeCourse(course);
    assertEquals(0, courseDaoList.size());
  }
}
