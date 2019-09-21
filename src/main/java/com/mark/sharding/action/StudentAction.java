package com.mark.sharding.action;

import com.mark.sharding.dao.OrderItemDao;
import com.mark.sharding.dao.StudentDao;
import com.mark.sharding.domain.OrderItem;
import com.mark.sharding.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: lulei
 * @Date: 2019/9/20 10:47
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/api/student")
public class StudentAction {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/add")
    public Integer addStudent(){
        for(long i=0;i<10; i++){
            Student student= new Student();
            student.setBookId(i);
            student.setStudentId(i);
            Integer cont = studentDao.addStudent(student);
            System.out.println(cont);
        }
        return 1;
    }

    @RequestMapping("/query")
    public Integer queryStudent(){
        for(long i=0;i<10; i++){
            List<Student> students = studentDao.queryStudent(i);
            System.out.println(students);
        }
        return 1;
    }
}
