package com.cupid.demo.entity;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author cupid
 * @date 2020-04-15
 */
@Data
public class UserEntity {
    @NotNull(message = "姓名不能为空")
    private String name;
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    @Size(min = 6,max = 11,message = "密码长度必须在6-11位之间")
    private String password;
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}