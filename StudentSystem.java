import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private Long rollNumber;
    private List<Integer> marks;
    private float average;

    public Student(String name, Long rollNumber, List<Integer> marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        calculateAverage();
    }

    public void calculateAverage() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        this.average = (float) total / marks.size();
    }

    public String getName() {
        return name;
    }

    public Long getRollNumber() {
        return rollNumber;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public float getAverage() {
        return average;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(Long rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
        calculateAverage();
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", rollNumber=" + rollNumber + ", marks=" + marks + ", average="
                + average + '}';
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(String name, Long rollNumber, List<Integer> marks) {
        students.add(new Student(name, rollNumber, marks));
        System.out.println(" Student added successfully!");
    }

    public void showStudents() {
        if (!students.isEmpty()) {
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
        }
        return;
    }

    public Student searchStudent(Long rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                System.out.println("You Searched " + s.toString());
                return s;
            }

        }
        System.out.println("Student not found");
        return null;

    }

    public void updateMarks(Long rollNumber, List<Integer> marks) {
        Student s = searchStudent(rollNumber);
        if (s != null) {
            s.setMarks(marks);
            s.calculateAverage();
            System.out.println("Marks Updated");
        } else {
            System.out.println(" Student not found.");
        }
    }

    public void deleteStudent(Long rollNumber) {
        Student s = searchStudent(rollNumber);
        if (s != null) {
            students.remove(s);
            System.out.println("Removed");
        }
        return;
    }

    public void Exit() {
        System.out.println("You are Exiting.. Bye!");
        return;
    }

}

public class StudentSystem {
    public static void main(String[] args) {
        StudentManager manage = new StudentManager();
        Scanner obj = new Scanner(System.in);
        List<Integer> marks1 = Arrays.asList(80, 90, 75, 88, 92);
        List<Integer> marks2 = Arrays.asList(70, 65, 85, 90, 78);
        List<Integer> marks3 = Arrays.asList(95, 88, 92, 85, 91);
        manage.addStudent("Alice", 101L, marks1);
        manage.addStudent("Bob", 102L, marks2);
        manage.addStudent("Charlie", 103L, marks3);
        while (true) {
            System.out.println("===== Student Grade Management System =====");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by roll number");
            System.out.println("4. Update student marks");
            System.out.println("5. Delete student");
            System.out.println("6. Exit");
            System.out.println("Enter your choice");
            int choice = obj.nextInt();
            obj.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter Name: ");
                    String name = obj.nextLine();
                    System.out.println("Enter Rollnumber:");
                    Long rollNumber = obj.nextLong();
                    obj.nextLine();
                    System.out.println("Enter Marks");
                    String marks = obj.nextLine();
                    List<Integer> markList = new ArrayList<>();
                    for (String mark : marks.split(",")) {
                        markList.add(Integer.parseInt(mark.trim()));
                    }
                    manage.addStudent(name, rollNumber, markList);
                    break;

                case 2:
                    manage.showStudents();
                    break;
                case 3:
                    System.out.print("Enter roll number to search: ");
                    Long searchRoll = obj.nextLong();
                    manage.searchStudent(searchRoll);
                    break;

                case 4:
                    System.out.print("Enter roll number to update: ");
                    Long updateRoll = obj.nextLong();
                    obj.nextLine();
                    System.out.print("Enter new marks (comma separated): ");
                    String newMarksInput = obj.nextLine();
                    List<Integer> newMarks = new ArrayList<>();
                    for (String m : newMarksInput.split(",")) {
                        newMarks.add(Integer.parseInt(m.trim()));
                    }
                    manage.updateMarks(updateRoll, newMarks);
                    break;
                case 5:
                    System.out.print("Enter roll number to delete: ");
                    Long deleteRoll = obj.nextLong();
                    manage.deleteStudent(deleteRoll);
                    break;

                case 6:
                    manage.Exit();
                    obj.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");

            }
        }

    }
}
