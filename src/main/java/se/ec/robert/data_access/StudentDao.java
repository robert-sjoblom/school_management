package se.ec.robert.data_access;

import java.util.ArrayList;
import se.ec.robert.models.Student;

public interface StudentDao {
  Student saveStudent(Student student);
  Student findByEmail(String email);
  ArrayList<Student> findByName(String name);
  Student findById(int id);
  ArrayList<Student> findAll();
  boolean deleteStudent(Student student);

}
