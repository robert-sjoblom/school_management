package se.ec.robert.data_access;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import se.ec.robert.models.Course;

public class CourseDaoList implements CourseDao {
  private static ArrayList<Course> courses = new ArrayList<>();

  @Override
  public Course saveCourse(Course course) {
    try {
      Course oldCourse = findById(course.getId());
      course.setStudents(oldCourse.getStudents());

      courses.remove(oldCourse);
    } catch (NoSuchElementException e) {}
    finally {
      courses.add(course);
    }
    return course;
  }

  @Override
  public Course findById(int id) throws NoSuchElementException {
    return courses.stream()
        .filter(s -> s.getId() == id)
        .reduce((a, b) -> null).get();
  }

  @Override
  public ArrayList<Course> findByName(String name) {
    return courses.stream()
        .filter(s -> s.getCourseName().equalsIgnoreCase(name))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public ArrayList<Course> findByDate(LocalDate date) {
    return courses.stream()
        .filter(s -> s.getStartDate().equals(date))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public ArrayList<Course> findAll() {
    return courses;
  }

  @Override
  public boolean removeCourse(Course course) {
    return courses.remove(course);
  }

  public void clear() {
    courses.clear();
  }

  public int size() {
    return courses.size();
  }
}
