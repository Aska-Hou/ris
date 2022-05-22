package edu.wku.ris.core.pojo.bo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/19 13:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "学生登录 视图对象", description = "")
@AllArgsConstructor
@NoArgsConstructor
public class StudentLogInBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;

    private String email;

    private String name;

    private Integer level;

    private String token;

    public StudentLogInBO(Long id, String email, String name, Integer level) {
        this.id = id;
        this.email = email;
        this.level = level;
        this.name = name;
    }
}
