package org.example.service;

import org.example.entity.Course;
import org.example.entity.Score;
import org.example.entity.Tc;
import org.example.entity.Teacher;
import org.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    public Object findAll()
    {
        List<Teacher> teacherList = teacherRepository.findAll();
        for(int i = 0; i < teacherList.size(); i++)
        {
            teacherList.get(i).password = "";
        }
        return teacherList;
    }
    public Boolean checkPassword(int uuid, String password)
    {
        Teacher teacher = teacherRepository.findByID(uuid);
        if(teacher.password.equals(password))
        {
            return Boolean.TRUE;
        }
        else
        {
            return Boolean.FALSE;
        }
    }
    public Teacher findById(int uuid)
    {
        Teacher teacher = teacherRepository.findByID(uuid);
        teacher.setPassword("");
        return teacher;
    }
    public Boolean register(String name,  String password, String sex, String major, int age)
    {
        int num = teacherRepository.countNum() + 1;
        teacherRepository.register(num, name, password, sex, major, age);
        return Boolean.TRUE;
    }
    public int countNum()
    {
        return teacherRepository.countNum();
    }
    public Boolean changePassword(int uuid, String password)
    {
        teacherRepository.changePassword(uuid,password);
        return Boolean.TRUE;
    }
    public Boolean addScore(int studentuuid, int courseuuid, int score, int Compulsory,int examscore, int dailyscore, int checkscore, int grade, String courseName, int credit, int is_updated)
    {
        teacherRepository.addScore(studentuuid, courseuuid, score, Compulsory, examscore, dailyscore, checkscore, grade, courseName, credit, is_updated);
        return Boolean.TRUE;
    }
    public Boolean addCourse(String name,int teacher_id,String major,int max_num,int Compulsory,int credit)
    {
        int num = teacherRepository.countCourseNum() + 1;
        teacherRepository.addCourse(num, name, teacher_id, major, max_num,Compulsory, credit);
        return true;
    }
    public Boolean addNotice(String title, String detail, Date time, int teacheruuid, String teachername)
    {
        int uuid = teacherRepository.countNoticeNum() + 1;
        teacherRepository.addNotice(uuid, title, detail, time, teacheruuid, teachername);
        return true;
    }
    public List<Score> selectScoreByCourseID(int courseuuid)
    {
        return teacherRepository.selectScoreByCourseID(courseuuid);
    }
    public List<Tc> selectCourseByTeacherID(int teacheruuid)
    {
        //System.out.println(teacheruuid);
        return teacherRepository.selectCourseByTeacherID(teacheruuid);
    }

    public List<Course>  selectCourseByMainTeacherID(int teacher_id)
    {
        return teacherRepository.selectCourseByMainTeacherID(teacher_id);
    }
    public List<Course> selectCourseByCourseID(int uuid)
    {
        return teacherRepository.selectCourseByCourseID(uuid);
    }
    public Boolean insertNoticeToStudent(int noticeuuid,int studentuuid,int teacheruuid)
    {
        teacherRepository.insertNoticeToStudent(noticeuuid, studentuuid, teacheruuid);
        return Boolean.TRUE;
    }
    public Boolean updateScoreByid(int score, int examscore, int dailyscore, int checkscore, int studentuuid, int courseuuid, int is_updated)
    {
        teacherRepository.updateScoreByid(score, examscore, dailyscore, checkscore, studentuuid, courseuuid, is_updated);
        return Boolean.TRUE;
    }
    public Boolean changeInfo(String name,  int uuid, String sex, String major, int age)
    {
        teacherRepository.changeInfo(name,uuid,sex,major,age);
        return Boolean.TRUE;
    }

}
