package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TS {
    public int studentuuid;
    public int teacheruuid;
    public int score;
    public String detail;
    public int courseuuid;
}
