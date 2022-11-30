package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Score {
    public int studentuuid;
    public int courseuuid;
    public int score;
    public int Compulsory;
    public int examscore;
    public int dailyscore;
    public int checkscore;
    public int grade;
    public String courseName;
    public int credit;
}
