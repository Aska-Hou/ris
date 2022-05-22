package edu.wku.ris.core.pojo.bo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/3/19 13:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PHD登录 视图对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class DoctorLogInBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String email;

    private String name;

    private Integer level;

    private String location;

    private String token;

    public DoctorLogInBO(Long id, String email, String name, Integer level){
        this.id = id;
        this.email = email;
        this.level = level;
        this.name = name;
    }
}
