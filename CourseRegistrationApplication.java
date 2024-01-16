import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    List<Student> enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public int registeredStudents() {
        return enrolledStudents.size();
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents() < capacity) {
            enrolledStudents.add(student);
            return true;
        } else {
            return false;
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
}

class Student {
    int studentID;
    String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }
}

class CourseRegistrationSystem {
    List<Course> courses;
    List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void displayCourseListing() {
        System.out.println("\nCourse Listing:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.courseCode);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("Available Slots: " + (course.capacity - course.registeredStudents()));
            System.out.println();
        }
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (course.registerStudent(student)) {
            System.out.println(student.name + " has successfully registered for " + course.title);
        } else {
            System.out.println("Sorry, the course " + course.title + " is already full. Registration failed.");
        }
    }

    public void removeStudentFromCourse(Student student, Course course) {
        if (course.registeredStudents() > 0) {
            course.removeStudent(student);
            System.out.println(student.name + " has successfully dropped " + course.title);
        } else {
            System.out.println(student.name + " is not registered for " + course.title + ". Removal failed.");
        }
    }
}

public class CourseRegistrationApplication {
    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Sample courses
        Course mathCourse = new Course("MATH101", "Introduction to Mathematics", "Basic math concepts", 30,
                "Mon/Wed/Fri 10:00 AM");
        Course compSciCourse = new Course("COMP101", "Introduction to Computer Science", "Programming fundamentals", 25,
                "Tue/Thu 2:00 PM");

        registrationSystem.courses.add(mathCourse);
        registrationSystem.courses.add(compSciCourse);

        // Sample students
        Student student1 = new Student(1, "John Doe");
        Student student2 = new Student(2, "Jane Smith");

        registrationSystem.students.add(student1);
        registrationSystem.students.add(student2);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nStudent Course Registration System Menu:");
            System.out.println("1. Display Course Listing");
            System.out.println("2. Register for a Course");
            System.out.println("3. Remove from a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registrationSystem.displayCourseListing();
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.next();

                    Student student = registrationSystem.students.stream()
                            .filter(s -> s.studentID == studentID)
                            .findFirst()
                            .orElse(null);

                    Course course = registrationSystem.courses.stream()
                            .filter(c -> c.courseCode.equals(courseCode))
                            .findFirst()
                            .orElse(null);

                    if (student != null && course != null) {
                        registrationSystem.registerStudentForCourse(student, course);
                    } else {
                        System.out.println("Invalid student ID or course code. Registration failed.");
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter course code to drop: ");
                    courseCode = scanner.next();

                    student = registrationSystem.students.stream()
                            .filter(s -> s.studentID == studentID)
                            .findFirst()
                            .orElse(null);

                    course = registrationSystem.courses.stream()
                            .filter(c -> c.courseCode.equals(courseCode))
                            .findFirst()
                            .orElse(null);

                    if (student != null && course != null) {
                        registrationSystem.removeStudentFromCourse(student, course);
                    } else {
                        System.out.println("Invalid student ID or course code. Removal failed.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the application. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        // Close the scanner
        scanner.close();
    }
}
