package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static StudentServiceTest.session;
import static StudentServiceTest.session;
import static StudentServiceTest.session;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;


class StudentServiceTest {
    public static Session session;

    @BeforeAll
    static void initialize() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    public void getStudentByEmail() {
        Student student = session.get(Student.class, "testuser@gmail.com");
        Assertions.assertTrue("testuser@gmail.com".equals(student.getEmail()) && "testpassword".equals(student.getPassword()));
    }

    @AfterAll
    static void close() {
        if (session != null) session.close();
    }
}



