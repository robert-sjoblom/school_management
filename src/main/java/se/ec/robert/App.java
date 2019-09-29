package se.ec.robert;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
  private final static Scanner sc = new Scanner(System.in);
  private final static SchoolManager manager = new SchoolManager();

  public static void main( String[] args ) {
    do {
      char choice;

      mainMenu();
      try {
        choice = sc.nextLine().charAt(0);
      } catch (StringIndexOutOfBoundsException e) {
        System.out.println("That's not a valid choice.");
        continue;
      }
      if(choice == 'q') { break; }

      mainMenuChoice(choice);
    } while(true);
  }

  private static void mainMenu() {
    System.out.println("1. Create course");
    System.out.println("2. Create student");
    System.out.println("3. Register student on course");
    System.out.println("4. Remove student from course");
    System.out.println("5. Search");
    System.out.println("6. Edit student");
    System.out.println("7. Edit course");
    System.out.println("q. Exit");
  }

  private static void mainMenuChoice(char choice) {
    switch(choice) {
      case '1':
        courseInformation();
        System.out.println(manager.createCourse(sc.nextLine()));
        break;
      case '2':
        studentInformation();
        System.out.println(manager.createStudent(sc.nextLine()));
        break;
      case '3':
        courseRegistryInformation();
        registerStudentOnCourse();
        break;
      case '4':
        courseUnregisterInformation();
        unregisterStudentOnCourse();
        break;
      case '5':
        searchMenu();
        break;
      case '6':
        editStudent();
        break;
      case '7':
        editCourse();
        break;
    }
  }

  private static void editCourse() {
    System.out.println("Specify course by id, then with the updated info.");
    System.out.println("Example:");
    System.out.println("1, Math, 2019-02-02, 12");

    String[] input = sc.nextLine().split(",");
    int id = Integer.parseInt(input[0]);

    String name = input[1].trim();
    LocalDate date = LocalDate.parse(input[2].trim());
    int weeks = Integer.parseInt(input[3].trim());

    System.out.println(manager.editCourse(id, name, date, weeks));
  }

  private static void editStudent() {
    System.out.println("Specify student by id, then with the updated info.");
    System.out.println("Example:");
    System.out.println("1, Bob, bob@new_email_address, Same_Old_Address");


    String[] input = sc.nextLine().split(",");
    int id = Integer.parseInt(input[0]);

    String name = input[1].trim();
    String email = input[2].trim();
    String address = input[3].trim();

    System.out.println(manager.editStudent(id, name, email, address));
  }

  private static void searchMenu() {
    do {
      char choice;
      searchMenuOptions();
      try {
        choice = sc.nextLine().charAt(0);
      } catch (StringIndexOutOfBoundsException e) {
        System.out.println("That's not a valid choice.");
        continue;
      }
      if(choice == 'z') { break; }
      searchOption(choice);
    } while(true);
  }

  private static void searchOption(char choice) {
    switch(choice) {
      case '1':
        searchStudentById();
        break;
      case '2':
        System.out.println("Search student by email:");
        System.out.println(manager.findStudentByEmail(sc.nextLine()));
        break;
      case '3':
        System.out.println("Search student by name:");
        System.out.println(manager.findStudentByName(sc.nextLine()));
        break;
      case '4':
        searchCourseById();
        break;
      case '5':
        System.out.println("Search course by name:");
        System.out.println(manager.findStudentByName(sc.nextLine()));
      case '6':
        System.out.println("Search course by date:");
        System.out.println(manager.findCourseByDate(
            LocalDate.parse(sc.nextLine()))
        );
        break;
    }
  }

  private static void searchCourseById() {
    System.out.println("Search course by id: ");
    int id = Integer.parseInt(sc.nextLine());
    System.out.println(id);
    try {
      System.out.println(manager.findCourseById(id));
    } catch (NoSuchElementException e) {
      System.out.println("No course by that id.\n");
    }
  }

  private static void searchStudentById() {
    System.out.println("Search student by id: ");
    int id = Integer.parseInt(sc.nextLine());
    System.out.println(id);
    try {
      System.out.println(manager.findStudentById(id));
    } catch (NoSuchElementException e) {
      System.out.println("No student by that id.\n");
    }
  }

  private static void searchMenuOptions() {
    System.out.println("1. Search student by id");
    System.out.println("2. Search student by email");
    System.out.println("3. Search student by name.");
    System.out.println("4. Search course by id");
    System.out.println("5. Search course by name");
    System.out.println("6. Search course by start date");
    System.out.println("z. return to previous menu");
  }

  private static void unregisterStudentOnCourse() {
    String[] selection = sc.nextLine().split(",");
    int studentId = Integer.parseInt(selection[0].trim());
    int courseId = Integer.parseInt(selection[1].trim());
    manager.removeStudentFromCourse(studentId, courseId);
    System.out.println(manager.findCourseById(courseId));
  }

  private static void courseUnregisterInformation() {
    System.out.println("Unregister students on courses with the following syntax:");
    System.out.println("Student Id, Course Id");
    System.out.println("The following courses and students are in the system:");
    System.out.println(manager.findAllStudents());
    System.out.println(manager.findAllCourses());
  }

  private static void registerStudentOnCourse() {
    String[] selection = sc.nextLine().split(",");
    int studentId = Integer.parseInt(selection[0].trim());
    int courseId = Integer.parseInt(selection[1].trim());
    manager.registerStudentOnCourse(studentId, courseId);
    System.out.println(manager.findCourseById(courseId));
  }

  private static void courseRegistryInformation() {
    System.out.println("Register students on courses with the following syntax:");
    System.out.println("Student Id, Course Id");
    System.out.println("The following courses and students are in the system:");
    System.out.println(manager.findAllStudents());
    System.out.println(manager.findAllCourses());
  }

  private static void studentInformation() {
    System.out.println("Enter the student information in the following syntax:");
    System.out.println("Name, email, address");
    System.out.println("Example:");
    System.out.println("Bob, bob@example.com, Example Street 1");
  }

  private static void courseInformation() {
    System.out.println("Enter the course information in the following syntax:");
    System.out.println("Name, date, duration in weeks");
    System.out.println("Example: ");
    System.out.println("Math, 2019-01-01, 12");
  }

}
