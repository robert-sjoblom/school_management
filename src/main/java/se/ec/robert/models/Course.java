package se.ec.robert.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Course {
  private final int id;
  private final String courseName;
  private final LocalDate startDate;
  private final int weekDuration;
  private List<Student> students;

  public Course(int id, String courseName, LocalDate startDate, int weekDuration) {
    this.id = id;
    this.courseName = courseName;
    this.startDate = startDate;
    this.weekDuration = weekDuration;
    this.students = new ArrayList<>();
  }

  public void register(Student student) {
    students.add(student);
    // if this returns itself, we can use the return to register the course on the student too
    // this could also just call on student itself and register self I guess
    // maybe that's the more pragmatic approach?
  }

  public void unregister(Student student) {
    try {
      students.remove(student);
    } catch (NoSuchElementException ignored) {}
  }

  public int getId() {
    return id;
  }

  public String getCourseName() {
    return courseName;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  private int getWeekDuration() {
    return weekDuration;
  }

  public List<Student> getStudents() {
    return students;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return getId() == course.getId() &&
        getWeekDuration() == course.getWeekDuration() &&
        getCourseName().equals(course.getCourseName()) &&
        getStartDate().equals(course.getStartDate()) &&
        getStudents().equals(course.getStudents());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getCourseName(), getStartDate(), getWeekDuration(), getStudents());
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + getId() +
        ", courseName='" + getCourseName() + '\'' +
        ", startDate=" + getStartDate() +
        ", weekDuration=" + getWeekDuration() +
        ", students=" + getStudents() +
        '}';
  }

  public void clear() {
    students.clear();
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}
