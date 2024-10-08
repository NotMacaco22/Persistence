package org.cash;

import org.cash.dao.StudentDAO;
import org.cash.models.Student;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
//        LocalDate date = LocalDate.parse("2005-04-10");
//        Student student = new Student("22011275", "Cesar", "Silva", date, "casilva@uamv.edu.ni");
//        studentDAO.saveStudent(student);
        studentDAO.getAllStudents().forEach(System.out::println);
//        studentDAO.deleteStudent("22011275");
    }
}
