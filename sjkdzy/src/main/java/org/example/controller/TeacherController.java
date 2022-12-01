package org.example.controller;

import lombok.val;
import org.example.Object.Result;
import org.example.Tools.token;
import org.example.entity.Course;
import org.example.entity.Tc;
import org.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/findAll")
    public Object findAll()
    {
        return teacherService.findAll();
    }
    @RequestMapping("/checkPassword")
    public Result checkPassword(@RequestParam(value = "uuid", required = true) int id, @RequestParam(value = "password", required = true) String password)
    {
        if(teacherService.checkPassword(id,password) == Boolean.TRUE)
        {
            Map<String, Object> data = new HashMap<String, Object>();
            String mytoken = token.token("teacher",Integer.toString(id), password);
            stringRedisTemplate.opsForValue().set(mytoken, id+"", 1800, TimeUnit.SECONDS);
            data.put("token", mytoken);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/register")
    public Result register(@RequestParam(value = "name") String name, @RequestParam(value = "sex")String sex,
                           @RequestParam(value = "age")int age, @RequestParam(value = "major")String major,
                           @RequestParam(value = "password")String password)
    {
        if(teacherService.register(name,password,sex,major,age) == true)
        {
            Map<String, Object> data = new HashMap<>();
            int id = teacherService.countNum();
            String mytoken = token.token("teacher",Integer.toString(id), password);
            data.put("token", mytoken);
            stringRedisTemplate.opsForValue().set(mytoken, id+"", 1, TimeUnit.DAYS);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/changePassword")
    public Result changePassword(@RequestParam(value = "uuid") int uuid, @RequestParam(value = "password")String password)
    {
        if(teacherService.changePassword(uuid, password) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "change successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/addScore")
    public Result addScore(@RequestParam(value = "studentuuid") int studentuuid, @RequestParam(value = "courseuuid") int courseuuid, @RequestParam(value = "score") int score,
                           @RequestParam(value = "Compulsory") int Compulsory,@RequestParam(value = "examscore")int examscore, @RequestParam(value = "dailyscore")int dailyscore,
                           @RequestParam(value = "checkscore")int checkscore, @RequestParam(value = "grade")int grade, @RequestParam(value = "courseName")String courseName,
                           @RequestParam(value = "credit")int credit,  @RequestParam(value = "is_updated")int is_updated)
    {
        if(teacherService.addScore(studentuuid, courseuuid, score, Compulsory, examscore, dailyscore, checkscore, grade, courseName, credit,is_updated) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "insert successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/findByID")
    public Result findByID(@RequestParam(value = "id") int uuid)
    {
        val teacher = teacherService.findById(uuid);
        if(teacher != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("teacher", teacher);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else {
            return Result.error();
        }
    }
    @RequestMapping("/addCourse")
    public Result addCourse(@RequestParam(value = "name")String name,@RequestParam(value = "teacher_id")int teacher_id,@RequestParam(value = "major")String major,@RequestParam(value = "max_num")int max_num,
                            @RequestParam(value = "Compulsory")int Compulsory,@RequestParam(value = "credit")int credit)
    {
        if(teacherService.addCourse(name, teacher_id, major, max_num,Compulsory,credit) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "insert successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/addNotice")
    public Result addNotice(@RequestParam(value = "title")String title, @RequestParam(value = "detail")String detail,
                            @RequestParam(value = "teacheruuid")int teacheruuid, @RequestParam(value = "teachername")String teachername)
    {
        Date time = new Date();
        if(teacherService.addNotice(title, detail, time, teacheruuid, teachername) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "insert successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectScoreByCourseID")
    public Result selectScoreByCourseID(@RequestParam(value = "courseuuid")int courseuuid)
    {
        val scoreByCourseID = teacherService.selectScoreByCourseID(courseuuid);
        if(scoreByCourseID != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("score", scoreByCourseID);
            Result r =Result.ok();
            r.setData(data);
            return r;
        }
        else{
            return Result.error();
        }
    }
    @RequestMapping("/selectCourseByTeacherID")
    public Result selectCourseByTeacherID(@RequestParam(value = "teacheruuid")int teacheruuid)
    {
        List<Tc> courseByTeacherID = teacherService.selectCourseByTeacherID(teacheruuid);
        List<List<Course>> listCourse = new ArrayList<>();
        if(courseByTeacherID != null)
        {
            for(Tc i : courseByTeacherID)
            {
                listCourse.add(teacherService.selectCourseByCourseID(i.getCourseuuid()));
            }
            Map<String, Object> data = new HashMap<>();
            data.put("listCourse", listCourse);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectCourseByMainTeacherID")
    public Result selectCourseByMainTeacherID(@RequestParam(value = "teacher_id") int teacher_id)
    {
        val courseList = teacherService.selectCourseByMainTeacherID(teacher_id);
        if(courseList != null)
        {
            Map<String , Object> data = new HashMap<>();
            data.put("CourseByMainTeacherID", courseList);
            val ok = Result.ok();
            ok.setData(data);
            return ok;
        }
        else {
            return Result.error();
        }
    }
    @RequestMapping("/selectCourseByCourseID")
    public Result selectCourseByCourseID(@RequestParam(value = "uuid")int uuid)
    {
        val courseList = teacherService.selectCourseByCourseID(uuid);
        if(courseList != null)
        {
            Map<String , Object> data = new HashMap<>();
            data.put("CourseByMainTeacherID", courseList);
            val ok = Result.ok();
            ok.setData(data);
            return ok;
        }
        else {
            return Result.error();
        }
    }
    @RequestMapping("/insertNoticeToStudent")
    public Result insertNoticeToStudent(@RequestParam(value = "noticeuuid") int noticeuuid,@RequestParam(value = "studentuuid") int studentuuid,@RequestParam(value = "teacheruuid") int teacheruuid)
    {
        if(teacherService.insertNoticeToStudent(noticeuuid, studentuuid, teacheruuid) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "insert successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/updateScoreByid")
    public Result updateScoreByid(@RequestParam(value = "score") int score, @RequestParam(value = "examscore")int examscore, @RequestParam(value = "dailyscore")int dailyscore,
                                  @RequestParam(value = "checkscore")int checkscore, @RequestParam(value = "studentuuid")int studentuuid, @RequestParam(value = "courseuuid")int courseuuid,
                                  @RequestParam(value = "is_updated")int is_updated)
    {
        if(teacherService.updateScoreByid(score, examscore, dailyscore, checkscore, studentuuid, courseuuid, is_updated) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "update successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/changeTeacherInfo")
    public Result changeTeacherInfo(@RequestParam(value = "name") String name, @RequestParam(value = "sex")String sex,
                                    @RequestParam(value = "age")int age, @RequestParam(value = "major")String major,
                                    @RequestParam(value = "uuid")int uuid)
    {
        if(teacherService.changeInfo(name,uuid,sex,major,age))
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "update successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectNoticeByTeaID")
    public Result selectNoticeByTeaID(@RequestParam(value = "teacheruuid") int teacheruuid)
    {
        val notices = teacherService.selectNoticeByTeaID(teacheruuid);
        if(notices != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("notices", notices);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
}

