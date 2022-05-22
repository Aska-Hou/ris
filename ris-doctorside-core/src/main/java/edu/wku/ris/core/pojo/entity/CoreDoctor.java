package edu.wku.ris.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aska
 * @since 2022-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CoreProfessor对象", description="")
public class CoreDoctor implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String name;

    private Integer level;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
