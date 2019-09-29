package se.ec.robert.data_access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.Student;

/**
 * Unit test for simple App.
 */
public class StudentDaoListTest {
  private StudentDaoList studentDaoList;
  private Student student = new Student(1, "Bob", "bob@example.com", "Backen 2");
  @Before
  public void setUp() {
    studentDaoList = new StudentDaoList();
  }

  @After
  public void tearDown() {
    studentDaoList.clear();
  }

  @Test
  public void saveStudent() {
    assertEquals(student, studentDaoList.saveStudent(student));
    assertEquals(1, studentDaoList.size());
  }

  @Test
  public void editStudent() {
    studentDaoList.saveStudent(student);
    Student student2 = new Student(1, "Bob", "new_email@exampke.com", "Backen 2");
    studentDaoList.saveStudent(student2);
    assertEquals(1, studentDaoList.size());
    assertEquals(student2, studentDaoList.findById(1));
  }

  @Test
  public void findByEmail() {
    studentDaoList.saveStudent(student);
    assertEquals(student, studentDaoList.findByEmail("bob@example.com"));
  }

  @Test(expected = NoSuchElementException.class)
  public void findByEmail_when_no_student_throws_exception() {
    studentDaoList.findByEmail("example@example.com");
  }

  @Test
  public void findByName() {
    Student student2 = new Student(2, "Bob", "bob@example.com", "Backen 2");
    Student student3 = new Student(3, "A", "bob@example.com", "Backen 2");

    studentDaoList.saveStudent(student);
    studentDaoList.saveStudent(student2);
    studentDaoList.saveStudent(student3);

    assertEquals(2, studentDaoList.findByName("Bob").size());
  }

  @Test
  public void findById() {
    studentDaoList.saveStudent(student);
    assertEquals(student, studentDaoList.findById(1));
  }

  @Test(expected = NoSuchElementException.class)
  public void findById_when_no_student_throws_exception() {
    studentDaoList.findById(2);
  }

  @Test
  public void deleteStudent() {
    studentDaoList.saveStudent(student);

    assertTrue(studentDaoList.deleteStudent(student));
    assertEquals(0, studentDaoList.size());
    assertFalse(studentDaoList.deleteStudent(student));
  }
}
