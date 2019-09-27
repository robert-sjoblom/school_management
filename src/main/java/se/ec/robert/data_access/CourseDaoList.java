package se.ec.robert.data_access;

import java.time.LocalDate;
import java.util.ArrayList;
import se.ec.robert.Course;

public class CourseDaoList implements CourseDao {
  private static ArrayList<Course> courses;

  @Override
  public Course saveCourse(Course course) {
    return null;
  }

  @Override
  public Course findById(int id) {
    return null;
  }

  @Override
  public ArrayList<Course> findByName(String name) {
    return null;
  }

  @Override
  public ArrayList<Course> findByDate(LocalDate date) {
    return null;
  }

  @Override
  public ArrayList<Course> findAll() {
    return null;
  }

  @Override
  public boolean removeCourse(Course course) {
    return false;
  }
}
