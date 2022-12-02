package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    public int uuid;
    public String name;
    public String sex;
    public int grade;
    public int class_num;
    public String major;
    public String password;
    public int isdeleted;
}
