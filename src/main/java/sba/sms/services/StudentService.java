package sba.sms.services;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentService is a concrete class. This class implements the
 * StudentI interface, overrides all abstract service methods and
 * provides implementation for each method. Lombok @Log used to
 * generate a logger file.
 */

public class StudentService implements StudentI {

    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = null;

    public void createStudent(Student newStudent) {
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(newStudent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
            }
            System.out.println(e);
        }
    }


    public Student getStudentByEmail(String email) {
        Student foundStudent = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            foundStudent = session.get(Student.class, email);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
            }
            System.out.println(e);
        }
        return foundStudent;
    }

    public boolean validateStudent(String email, String password) {
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            String hqlString = "FROM  Student WHERE email = :email AND password = :password";
            Query<Student> query = session.createQuery(hqlString, Student.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            Student student = query.getSingleResult();
            transaction.commit();
            return student != null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
            }
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {

    }

    @Override
    public List<Course> getStudentCourses(String email) {
        return List.of();
    }

    public List<Student> getAllStudents() {
        Transaction transaction = null;
        try {
            String hqlString = "SELECT Student from Students";
            Query<Student> query = session.createQuery(hqlString, Student.class);
            return query.getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
            }
            System.out.println(e);
            return null;
        }
    }
}



