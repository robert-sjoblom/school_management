package se.ec.robert.data_access;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import se.ec.robert.models.Student;

public class StudentDaoList implements StudentDao {
  private final static ArrayList<Student> students = new ArrayList<>();

  @Override
  public Student saveStudent(Student student) {
    try {
      students.remove(findById(student.getId()));
    } catch (NoSuchElementException e) {}
    finally {
      students.add(student);
    }

    return student;
  }

  @Override
  public Student findByEmail(String email) throws NoSuchElementException {
    return students.stream()
        .filter(s -> s.getEmail().equalsIgnoreCase(email))
        .reduce((a, b) -> null).get();
  }

  @Override
  public ArrayList<Student> findByName(String name) {
    return students.stream()
        .filter(s -> s.getName().equalsIgnoreCase(name))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public Student findById(int id) throws NoSuchElementException {
    return students.stream()
        .filter(s -> s.getId() == id)
        .reduce((a, b) -> null).get();
  }

  @Override
  public ArrayList<Student> findAll() {
    return students;
  }

  @Override
  public boolean deleteStudent(Student student) {
    return students.remove(student);
  }

  public void clear() {
    students.clear();
  }

  public int size() {
    return students.size();
  }
}
