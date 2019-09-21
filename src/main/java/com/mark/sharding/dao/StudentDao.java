package com.mark.sharding.dao;

import com.mark.sharding.domain.Order;
import com.mark.sharding.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: lulei
 * @Date: 2019/9/20 11:02
 * @Description:
 */
public interface StudentDao {
    Integer addStudent(Student student);
    List<Student> queryStudent(@Param("studentId") Long studentId);
}
