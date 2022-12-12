package org.example.controller;

import lombok.val;
import org.example.Object.Result;
import org.example.Tools.token;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/findAll")
    public Object findAll()
    {
        return studentService.findAll();
    }
    @RequestMapping("/checkPassword")
    public Result checkPassword(@RequestParam(value = "uuid", required = true) int id, @RequestParam(value = "password", required = true) String password)
    {
        if(studentService.checkPassword(id,password) == Boolean.TRUE)
        {
            Map<String, Object> data = new HashMap<String, Object>();
            String mytoken = token.token("student",Integer.toString(id), password);
            stringRedisTemplate.opsForValue().set(mytoken,id+"", 1800, TimeUnit.SECONDS);
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
    public Result register(@RequestParam(value = "name") String name, @RequestParam(value = "sex")String sex, @RequestParam(value = "grade")int grade,
                           @RequestParam(value = "class_num")int class_num, @RequestParam(value = "major")String major,
                           @RequestParam(value = "password")String password)
    {
        if(studentService.register(name, sex, grade, class_num, major, password) == true)
        {
            Map<String, Object> data = new HashMap<>();
            int id = studentService.countNum();
            String mytoken = token.token("student",Integer.toString(id), password);
            stringRedisTemplate.opsForValue().set(mytoken, id+"", 1, TimeUnit.DAYS);
            data.put("token", mytoken);
            data.put("id", id);
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
        if(studentService.changePassword(uuid, password) == true)
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
    @RequestMapping("/selectScoreByid")
    public Result selectScoreByid(@RequestParam(value = "studentuuid") int studentuuid)
    {
        val scores = studentService.selectScoreByid(studentuuid);
        if(scores != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("Score", scores);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectCourseBycourseid")
    public Result selectCourseBycourseid(@RequestParam("uuid") int uuid)
    {
        val course = studentService.selectCourseBycourseid(uuid);
        if(course != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("course", course);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectCourseBymajor")
    public Result selectCourseBymajor(@RequestParam("major") String major)
    {
        val course = studentService.selectCourseBymajor(major);
        if(course != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("course", course);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectCourseByname")
    public Result selectCourseByname(@RequestParam("name") String name)
    {
        val course = studentService.selectCourseByname(name);
        if(course != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("course", course);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectCourseByteacherID")
    public Result selectCourseByteacherID(@RequestParam(value = "teacher_id") int teacher_id)
    {
        val course = studentService.selectCourseByteacherID(teacher_id);
        if(course != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("course", course);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/selectTeacherByName")
    public Result selectTeacherByName(@RequestParam(value = "name") String name)
    {
        val teachers = studentService.selectTeacherByName(name);
        if(teachers != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("teachers", teachers);
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else
        {
            return Result.error();
        }
    }
    @RequestMapping("/insertTeacherScore")
    public Result insertTeacherScore(@RequestParam(value = "studentuuid") int studentuuid,@RequestParam(value = "teacheruuid")int teacheruuid, @RequestParam(value = "score")int score,
                                     @RequestParam(value ="detail" )String detail,@RequestParam(value = "courseuuid")int courseuuid)
    {
        if(studentService.insertTeacherScore(studentuuid, teacheruuid, score, detail, courseuuid) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "insert successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else {
            return Result.error();
        }
    }
    @RequestMapping("/findByID")
    public Result findByID(@RequestParam(value = "id") int uuid)
    {
        val student = studentService.findByID(uuid);
        if(student != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("student", student);
//            System.out.println("okk");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else {
            return Result.error();
        }
    }
    @RequestMapping("/insertCourseByid")
    public Result insertCourseByid(@RequestParam(value = "studentuuid")int studentuuid,@RequestParam(value = "courseuuid") int courseuuid)
    {
        if(studentService.insertCourseByid(studentuuid, courseuuid) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "insert successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else {
            return Result.error();
        }
    }
    @RequestMapping("/deleteCourseByid")
    public Result deleteCourseByid(@RequestParam(value = "studentuuid")int studentuuid,@RequestParam(value = "courseuuid") int courseuuid)
    {
        if(studentService.deleteCourseByid(studentuuid, courseuuid) == true)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "delete successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else {
            return Result.error();
        }
    }
    @RequestMapping("/changeStudentInfo")
    public Result changeStudentInfo(@RequestParam(value = "name")String name,@RequestParam(value = "sex") String sex,@RequestParam(value = "grade") int grade, @RequestParam(value = "class_num")int class_num,
                                    @RequestParam(value = "major")String major,@RequestParam(value = "uuid") int uuid)
    {
        if(studentService.changeStudentInfo(name, sex, grade, class_num, major, uuid))
        {
            Map<String, Object> data = new HashMap<>();
            data.put("result", "update successful");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }
        else {
        return Result.error();
    }
    }
    @RequestMapping("/selectNoticeByStuID")
    public Result selectNoticeByStuID(@RequestParam(value = "stuuuid")int stuuuid)
    {
        val notices = studentService.selectNoticeByStuID(stuuuid);
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
    @RequestMapping("/addCourseByIDList")
    public Result addCourseByIDList(@RequestParam(value = "studentuuid")int studentuuid,@RequestParam(value = "IDList") List<Integer> idList)
    {
        try {
            for(int i = 0; i < idList.size(); i++)
            {
                studentService.insertCourseByid(studentuuid, idList.get(i));
            }
            Map<String, Object> data = new HashMap<>();
            data.put("result", "插入成功");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }catch (Exception err)
        {
            return Result.error();
        }

    }

    @RequestMapping("/deleteCourseByIDList")
    public Result deleteCourseByIDList(@RequestParam(value = "studentuuid")int studentuuid,@RequestParam(value = "IDList") List<Integer> idList)
    {
        try {
            for(int i = 0; i < idList.size(); i++)
            {
                studentService.deleteCourseByid(studentuuid, idList.get(i));
            }
            Map<String, Object> data = new HashMap<>();
            data.put("result", "删除成功");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }catch (Exception err)
        {
            return Result.error();
        }
    }
    @RequestMapping("/deleteStudent")
    public Result deleteStudent(@RequestParam(value = "uuid")int uuid)
    {
        try {
            studentService.deleteStudent(uuid);
            Map<String, Object> data = new HashMap<>();
            data.put("result", "删除成功");
            Result r = Result.ok();
            r.setData(data);
            return r;
        }catch (Exception e)
        {
            return Result.error();
        }
    }
}
