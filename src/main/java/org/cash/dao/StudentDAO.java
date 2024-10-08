package org.cash.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.cash.interfaces.IStudent;
import org.cash.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudentDAO implements IStudent {

    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    @Override
    public void saveStudent(Student student) {
        EntityManager entityManager = null;
        try{
            entityManager = EntityManagerAdmin.getInstance();
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            logger.info("Student with CIF: " + student.getCif() + " saved successfully");
        } catch (Exception e){
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error saving student with CIF" + student.getCif() + "\nError: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Student searchById(String cif) {
        EntityManager entityManager = null;
        Student student = null;
        try {
            entityManager = EntityManagerAdmin.getInstance();
            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.cif = :cif", Student.class);
            query.setParameter("cif", cif);
            student = query.getSingleResult();
        } catch (Exception e){
            logger.severe("Error searching student with CIF " + cif + "\nError: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        EntityManager entityManager = null;
        try {
            entityManager = EntityManagerAdmin.getInstance();
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error updating student with CIF " + student.getCif() + "\nError: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteStudent(String cif) {
        EntityManager entityManager = null;
        try {
            entityManager = EntityManagerAdmin.getInstance();
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, cif);
            if (student != null) {
                entityManager.remove(student);
                entityManager.getTransaction().commit();
            } else {
                logger.warning("No student found with CIF: " + cif);
                entityManager.getTransaction().rollback();
            }

        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error deleting student with CIF " + cif + "\nError: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


    @Override
    public List<Student> getAllStudents() {
        EntityManager entityManager = null;
        List<Student> students = new ArrayList<>();
        try {
            entityManager = EntityManagerAdmin.getInstance();
            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
            students = query.getResultList();
        } catch (Exception e){
            logger.severe("Error getting the list of students\nError: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        if (students.isEmpty()) {logger.info("No students found");}
        return students;
    }

}
