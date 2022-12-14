package org.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
@Mapper
public interface TeacherRepository {
    List<Teacher> findAll();
    Teacher findByID(int id);
    int register(int uuid, String name,  String password, String sex, String major, int age);
    int countNum();
    int changePassword(int uuid, String password);


    int addScore(int studentuuid, int courseuuid, int score, int Compulsory,int examscore, int dailyscore,
                 int checkscore, int grade, String courseName, int credit, int is_updated);
    int addCourse(int uuid,String name,int teacher_id,String major,int max_num, int Compulsory,int credit, int isdeleted);
    int countCourseNum();
    int countNoticeNum();
    int addNotice(int uuid, String title, String detail, Date time, int teacheruuid, String teachername, int isdeleted);
    List<Score> selectScoreByCourseID(int courseuuid);
    List<Tc> selectCourseByTeacherID(int teacheruuid);
    List<Course> selectCourseByMainTeacherID(int teacher_id);
    List<Course> selectCourseByCourseID(int uuid);
    int insertNoticeToStudent(int noticeuuid,int studentuuid,int teacheruuid);
    int updateScoreByid(int score, int examscore, int dailyscore, int checkscore, int studentuuid, int courseuuid, int is_updated);
    int changeInfo(String name,  int uuid, String sex, String major, int age);
    List<Notice> selectNoticeByTeaID(int teacheruuid);
    int addTeacherToCourse(int teacheruuid, int courseuuid);
    List<Course> selectAllCourseByTeacherID(int teacheruuid);
    List<TS> selectTeacherScoreByTeacherID(int teacheruuid);
    List<TS> selectTeacherScoreByTeacherIDAndCourseID(int teacheruuid, int courseuuid);
    int deleteNoticeByNoticeID(int uuid);
    int deleteCourseByCourseID(int uuid);
    int deleteTeacher(int uuid);
}
