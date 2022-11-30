package org.example.Object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    Boolean Success;
    Integer code;
    String message;
    Map<String, Object> data;

    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(100);
        r.setMessage("成功");
        return r;
    }


    public static Result error(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(101);
        r.setMessage("失败");
        return r;
    }
}
