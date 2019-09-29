package se.ec.robert.data_access;

import java.time.LocalDate;
import java.util.ArrayList;
import models.Course;

public interface CourseDao {
  Course saveCourse(Course course);
  Course findById(int id);
  ArrayList<Course> findByName(String name); // maybe arraylist. not decided yet.
  ArrayList<Course> findByDate(LocalDate date);
  ArrayList<Course> findAll();
  boolean removeCourse(Course course);
}
