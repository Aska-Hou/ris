package edu.wku.ris.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

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
 * @since 2022-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CoreStudent对象", description = "")
public class CoreStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private Integer level;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
