package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    public int uuid;
    public String name;
    public String password;
    public String sex;
    public String major;
    public int age;
}
