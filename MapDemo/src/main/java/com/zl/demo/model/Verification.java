package com.zl.demo.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 参数校验测试model
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-10-11 14:31
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
public class Verification implements Serializable {
    @NotBlank(message="年龄不能为空")
    @Pattern(regexp="^[0-9]{1,2}$",message="年龄不正确")
    private String age;

    @Length(min = 5, max = 17, message = "name长度在[5,17]之间")
    private String name;

    @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
    private String length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
