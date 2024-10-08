package org.cash.interfaces;

import org.cash.models.Student;
import java.util.List;

public interface IStudent {

    // CRUD
    public void saveStudent(Student student);   // Create (On Database)
    public Student searchById(String cif);      // Read
    public void updateStudent(Student student); // Update
    public void deleteStudent(String cif); // Delete

    public List<Student> getAllStudents();

}
