package com.mark.sharding.dao;

import com.mark.sharding.dao.OrderItemDao;
import com.mark.sharding.dao.StudentDao;
import com.mark.sharding.domain.OrderItem;
import com.mark.sharding.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTests {

    @Autowired
    private StudentDao studentDao;


    @Test
    public void addStudent() {

        for(long i=0;i<10; i++){
            Student student= new Student();
            student.setBookId(i);
            student.setStudentId(i);
            Integer cont = studentDao.addStudent(student);
            System.out.println(cont);
        }
    }

    @Test
    public void queryStudent() {

        for(long i=0;i<10; i++){
            List<Student> students = studentDao.queryStudent(i);
            System.out.println(students);
        }
    }

}
