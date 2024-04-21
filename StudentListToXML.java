package XML;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentListToXML {
    public static void main(String[] args) {
        // Nhập thông tin sinh viên từ người dùng
        ArrayList<Student> studentList = inputStudentInformation();

        // Tạo XML từ danh sách sinh viên
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<students>\n");
        for (Student student : studentList) {
            xmlBuilder.append("\t<student>\n");
            xmlBuilder.append("\t\t<name>" + student.getName() + "</name>\n");
            xmlBuilder.append("\t\t<age>" + student.getAge() + "</age>\n");
            xmlBuilder.append("\t\t<gpa>" + student.getGpa() + "</gpa>\n");
            xmlBuilder.append("\t</student>\n");
        }
        xmlBuilder.append("</students>");

        // Ghi XML ra file
        try {
            FileWriter writer = new FileWriter("student_list.xml");
            writer.write(xmlBuilder.toString());
            writer.close();
            System.out.println("Danh sách sinh viên đã được ghi vào file 'student_list.xml'");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi file XML: " + e.getMessage());
        }
    }

    // Phương thức để nhập thông tin của sinh viên từ người dùng
    private static ArrayList<Student> inputStudentInformation() {
        ArrayList<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số lượng sinh viên: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Nhập thông tin cho sinh viên thứ " + (i + 1) + ":");
            System.out.print("Tên: ");
            String name = scanner.nextLine();
            System.out.print("Tuổi: ");
            int age = scanner.nextInt();
            System.out.print("GPA: ");
            double gpa = scanner.nextDouble();
            scanner.nextLine(); // Consume newline left-over

            studentList.add(new Student(name, age, gpa));
        }

        scanner.close();
        return studentList;
    }
}

// Định nghĩa lớp Sinh viên
class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
}
