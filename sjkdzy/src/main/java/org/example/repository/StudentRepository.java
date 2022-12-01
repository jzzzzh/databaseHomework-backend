package org.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface StudentRepository {
    List<Student> findAll();
    Student findByID(int id);
    int register(int uuid, String name, String sex, int grade, int class_num, String major, String password);
    int countNum();
    int changePassword(int uuid, String password);
    List<Score> selectScoreByid(int studentuuid);
    Course selectCourseBycourseid(int uuid);
    List<Course> selectCourseBymajor(String major);
    List<Course> selectCourseByname(String name);
    List<Course> selectCourseByteacherID(int teacher_id);
    List<Teacher> selectTeacherByName(String name);
    int insertTeacherScore(int studentuuid, int teacheruuid, int score,String detail,int courseuuid);
    int insertCourseByid(int studentuuid, int courseuuid, int score, int Compulsory, int examscore, int dailyscore,int checkscore, int grade, String courseName,int credit, int is_updated);
    int deleteCourseByid(int studentuuid, int courseuuid);
    int changeStudentInfo(String name, String sex, int grade, int class_num, String major, int uuid);
    List<NS> selectNoticeIDByStudentID(int studentuuid);
    Notice selectNoticeByNoticeID(int uuid);
    List<Notice> selectNoticeByStuID(int studentuuid);
}
