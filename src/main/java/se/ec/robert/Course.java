package se.ec.robert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
  private int id;
  private String courseName;
  private LocalDate startDate;
  private int weekDuration;
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
  }

  public void unregister(Student student) {
    students.remove(student);
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

  public int getWeekDuration() {
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
}
