package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    public int uuid;
    public String name;
    public int teacher_uuid;
    public String major;
    public int max_num;
    public int Compulsory;
    public int credit;
}
