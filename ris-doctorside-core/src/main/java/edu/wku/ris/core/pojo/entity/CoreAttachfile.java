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
 *
 * @author Aska
 * @since 2022-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CoreAttachfile对象", description="")
public class CoreAttachfile implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String fileType;

    private Long letterId;

    private LocalDate uploadDate;

    private String link;

    private String classify;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
