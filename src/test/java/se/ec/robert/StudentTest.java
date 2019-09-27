package se.ec.robert;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentTest {

  @Test
  public void testToString() {
    Student st = new Student(1, "Bob", "bob@example.com", "Backen 2");
    assertEquals(st.toString(),
                 "Student{id=1, name='Bob', email='bob@example.com', address='Backen 2'}");
  }

  @Test
  public void testEquals() {
    assertEquals(new Student(1, "Bob", "bob@example.com", "Backen 2"),
                 new Student(1, "Bob", "bob@example.com", "Backen 2"));
  }
}