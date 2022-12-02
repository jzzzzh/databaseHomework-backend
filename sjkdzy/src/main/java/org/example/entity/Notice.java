package org.example.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notice {
    public int uuid;
    public String title;
    public String detail;
    public Date time;
    public int teacheruuid;
    public String teachername;
    public int isdeleted;
}
