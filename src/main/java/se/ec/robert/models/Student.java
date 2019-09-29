package se.ec.robert.models;

import java.util.Objects;

public class Student {
  private final int id;
  private final String name;
  private final String email;
  private final String address;

  public Student(int id, String name, String email, String address) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  private String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + getId() +
        ", name='" + getName() + '\'' +
        ", email='" + getEmail() + '\'' +
        ", address='" + getAddress() + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return getId() == student.getId() &&
        getName().equals(student.getName()) &&
        getEmail().equals(student.getEmail()) &&
        getAddress().equals(student.getAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getEmail(), getAddress());
  }
}
