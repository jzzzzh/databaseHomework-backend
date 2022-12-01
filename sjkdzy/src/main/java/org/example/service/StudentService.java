package org.example.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.val;
import org.example.entity.*;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Object findAll()
    {
        List<Student> studentlist = studentRepository.findAll();
        for (int i = 0; i < studentlist.size(); i++)
        {
            studentlist.get(i).password = "";
        }
        return studentlist;
    }
    public Boolean checkPassword(int uuid, String myPassword)
    {
        Student student = studentRepository.findByID(uuid);
        if(myPassword.equals(student.password))
        {
            return Boolean.TRUE;
        }
        else
        {
            return Boolean.FALSE;
        }
    }
    public Boolean register(String name, String sex, int grade, int class_num, String major, String password)
    {
        int num = studentRepository.countNum() + 1;
        studentRepository.register(num,name,sex,grade,class_num,major,password);
        return Boolean.TRUE;
    }
    public Boolean changePassword(int uuid, String password)
    {
        studentRepository.changePassword(uuid,password);
        return Boolean.TRUE;
    }
    public List<Score> selectScoreByid(int studentuuid)
    {
        return studentRepository.selectScoreByid(studentuuid);
    }
    public int countNum()
    {
        return studentRepository.countNum();
    }
    public Student findByID(int uuid)
    {
        val student = studentRepository.findByID(uuid);
        student.setPassword("");
        return student;
    }
    public Course selectCourseBycourseid(int uuid)
    {
        return studentRepository.selectCourseBycourseid(uuid);
    }
    public List<Course> selectCourseBymajor(String major)
    {
        return studentRepository.selectCourseBymajor(major);
    }
    public List<Course> selectCourseByname(String name)
    {
        return studentRepository.selectCourseByname(name);
    }
    public List<Course> selectCourseByteacherID(int teacher_id)
    {
        return studentRepository.selectCourseByteacherID(teacher_id);
    }
    public List<Teacher> selectTeacherByName(String name)
    {
        return studentRepository.selectTeacherByName(name);
    }
    public Boolean insertTeacherScore(int studentuuid, int teacheruuid, int score,String detail,int courseuuid)
    {
        studentRepository.insertTeacherScore(studentuuid, teacheruuid, score, detail, courseuuid);
        return true;
    }
    public Boolean insertCourseByid(int studentuuid, int courseuuid)
    {
        //改
        val course = studentRepository.selectCourseBycourseid(courseuuid);
        val student = studentRepository.findByID(studentuuid);
        int Compulsory = course.getCompulsory();
        int grade = student.getGrade();
        String courseName = course.getName();
        int credit = course.getCredit();
        studentRepository.insertCourseByid(studentuuid, courseuuid, 0, Compulsory, 0, 0, 0, grade, courseName, credit, 0);
        return true;
    }
    public Boolean deleteCourseByid(int studentuuid, int courseuuid)
    {
        studentRepository.deleteCourseByid(studentuuid, courseuuid);
        return true;
    }
    public Boolean changeStudentInfo(String name, String sex, int grade, int class_num, String major, int uuid)
    {
        studentRepository.changeStudentInfo(name, sex, grade, class_num, major, uuid);
        return true;
    }
    public List<Notice> selectNoticeByStuID(int studentuuid)
    {
        return studentRepository.selectNoticeByStuID(studentuuid);
    }
}
